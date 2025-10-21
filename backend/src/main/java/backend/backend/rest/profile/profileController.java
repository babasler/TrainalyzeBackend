package backend.backend.rest.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.rest.profile.DTO.ProfileInputDTO;
import backend.backend.rest.profile.DTO.ProfileOutputDTO;
import backend.backend.rest.profile.Mapper.ProfileInputDtoToProfileMapper;
import backend.backend.rest.profile.Mapper.ProfileToProfileOutputDtoMapper;
import backend.backend.rest.profile.bodyweight.BodyWeight;
import backend.backend.rest.profile.bodyweight.BodyWeightDTO;
import backend.backend.rest.profile.bodyweight.BodyWeightService;
import backend.backend.rest.user.User;
import backend.backend.rest.user.UserService;

@RestController
@RequestMapping("/trainalyze/profile")
public class profileController {
    private final profileService profileService;
    private final UserService userService;
    private final BodyWeightService bodyWeightService;
    private final Logger logger = LoggerFactory.getLogger(profileController.class);

    public profileController(profileService profileService, UserService userService, BodyWeightService bodyWeightService) {
        this.profileService = profileService;
        this.userService = userService;
        this.bodyWeightService = bodyWeightService;
    }

    @GetMapping("/current")
    public ResponseEntity<ProfileOutputDTO> getCurrentProfile(Authentication authentication) {
        logger.info("Fetching current profile");
        String username = authentication.getName();
        logger.info("Authenticated user: {}", username);
        Profile profile = profileService.getProfile(username);
        if (profile != null) {
            logger.info("Current profile: {}, {}, {}, {}, {}, {}, {}, {},", profile.getUsername(),profile.getWeightIncreaseType(),
                    profile.getIncreaseWeight(), profile.getIncreaseAtReps(), profile.getWorkoutSelection(),
                    profile.getSelectedTrainingsplan(), profile.getHandleMissingWorkout(), profile.getBodyHeight(),
                    profile.getBodyWeight(), profile.getBmi());
            return ResponseEntity.ok(ProfileToProfileOutputDtoMapper.map(profile));
        } else {
            logger.warn("No profile found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateProfile(@RequestBody ProfileInputDTO profileInputDTO) {
        logger.info("Received profile: {}", profileInputDTO);
        Profile profile = ProfileInputDtoToProfileMapper.map(profileInputDTO);
        profileService.updateProfile(profile);
        logger.info("Profile updated successfully");
        return ResponseEntity.ok(Map.of("message", "Profile updated successfully"));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerProfile(@RequestBody RegisterRequest registerRequest) {
        logger.info("Registering profile with username: {}", registerRequest.getUsername());
        try{
            User user = userService.registerUser(registerRequest.getUsername(), registerRequest.getPin());
            profileService.createProfile(user, registerRequest.getUsername());
        }
        catch (Exception e) {
            logger.error("Error registering user: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("message", "Registration failed: " + e.getMessage()));
        }
    
        logger.info("Profile registered successfully");
        return ResponseEntity.ok(Map.of("message", "Profile registered successfully"));
    }

    @GetMapping("/bodyweight/current")
    public ResponseEntity<BodyWeightDTO> getCurrentBodyWeight(Authentication authentication) {
        logger.info("Fetching current body weight");
        String username = authentication.getName();
        Profile profile = profileService.getProfile(username);
        
        if (profile == null) {
            logger.warn("No profile found for user: {}", username);
            return ResponseEntity.notFound().build();
        }
        
        BodyWeight currentBodyWeight = bodyWeightService.getCurrentBodyWeightForProfile(profile);
        
        if (currentBodyWeight != null) {
            BodyWeightDTO bodyWeightDTO = new BodyWeightDTO();
            bodyWeightDTO.setBodyWeight(currentBodyWeight.getBodyWeight());
            bodyWeightDTO.setDate(currentBodyWeight.getDate().toString());
            logger.info("Current body weight: {}, Date: {}", bodyWeightDTO.getBodyWeight(), bodyWeightDTO.getDate());
            return ResponseEntity.ok(bodyWeightDTO);
        } else {
            logger.warn("No body weight records found for user: {}", username);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bodyweight/history")
    public ResponseEntity<List<BodyWeightDTO>> getBodyWeightHistory(
            Authentication authentication, 
            @RequestParam String period) {
        logger.info("Fetching body weight history for period: {}", period);
        String username = authentication.getName();
        Profile profile = profileService.getProfile(username);
        
        if (profile == null) {
            logger.warn("No profile found for user: {}", username);
            return ResponseEntity.notFound().build();
        }
        
        List<BodyWeight> bodyWeightHistory = bodyWeightService.getBodyWeightsByPeriodForProfile(profile, period);
        List<BodyWeightDTO> bodyWeightDTOs = new ArrayList<>();
        
        for (BodyWeight bodyWeight : bodyWeightHistory) {
            BodyWeightDTO dto = new BodyWeightDTO();
            dto.setBodyWeight(bodyWeight.getBodyWeight());
            dto.setDate(bodyWeight.getDate().toString());
            bodyWeightDTOs.add(dto);
        }
        
        logger.info("Found {} body weight records for user: {}", bodyWeightDTOs.size(), username);
        return ResponseEntity.ok(bodyWeightDTOs);
    }

    @PostMapping("/bodyweight/add")
    public ResponseEntity<Map<String, String>> addBodyWeight(
            Authentication authentication,
            @RequestBody BodyWeightDTO bodyWeightDTO) {
        logger.info("Adding body weight entry: {}", bodyWeightDTO);
        String username = authentication.getName();
        Profile profile = profileService.getProfile(username);
        
        if (profile == null) {
            logger.warn("No profile found for user: {}", username);
            return ResponseEntity.notFound().build();
        }
        
        try {
            java.util.Date date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(bodyWeightDTO.getDate());
            bodyWeightService.saveBodyWeightForProfile(profile, bodyWeightDTO.getBodyWeight(), date);
            
            // Aktualisiere auch das bodyWeight Feld im Profil, falls es das neueste ist
            BodyWeight currentBodyWeight = bodyWeightService.getCurrentBodyWeightForProfile(profile);
            if (currentBodyWeight != null && currentBodyWeight.getDate().equals(date)) {
                profile.setBodyWeight(bodyWeightDTO.getBodyWeight());
                if (profile.getBodyHeight() > 0) {
                    profile.setBmi(bodyWeightDTO.getBodyWeight() / ((profile.getBodyHeight() / 100) * (profile.getBodyHeight() / 100)));
                }
                profileService.updateBodyWeightFieldsOnly(profile);
            }
            
            logger.info("Body weight entry added successfully for user: {}", username);
            return ResponseEntity.ok(Map.of("message", "Body weight added successfully"));
        } catch (Exception e) {
            logger.error("Error adding body weight: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("message", "Failed to add body weight: " + e.getMessage()));
        }
    }

}
