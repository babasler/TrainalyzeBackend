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

import backend.backend.rest.auth.CustomUserDetails;
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
    private final ProfileToProfileOutputDtoMapper profileToProfileOutputDtoMapper;

    public profileController(profileService profileService, UserService userService, BodyWeightService bodyWeightService, ProfileToProfileOutputDtoMapper profileToProfileOutputDtoMapper) {
        this.profileService = profileService;
        this.userService = userService;
        this.bodyWeightService = bodyWeightService;
        this.profileToProfileOutputDtoMapper = profileToProfileOutputDtoMapper;
    }

    @GetMapping("/current")
    public ResponseEntity<ProfileOutputDTO> getCurrentProfile(Authentication authentication) {
        logger.info("Fetching current profile");
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long profileId = userDetails.getProfileId();
        
        if (profileId == null) {
            // Fallback: User hat kein Profil, erstelle eins automatisch
            logger.warn("User {} has no profile, creating one automatically", userDetails.getUsername());
            try {
                User user = userService.findByUsername(userDetails.getUsername());
                if (user != null) {
                    profileService.createProfile(user, userDetails.getUsername());
                    // User neu laden um Profile-ID zu bekommen
                    user = userService.findByUsername(userDetails.getUsername());
                    profileId = user.getProfile().getId();
                    logger.info("Profile created automatically for user {}", userDetails.getUsername());
                } else {
                    logger.error("User not found: {}", userDetails.getUsername());
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                logger.error("Failed to auto-create profile: {}", e.getMessage());
                return ResponseEntity.notFound().build();
            }
        }
        
        logger.info("Authenticated user profile ID: {}", profileId);
        
        Profile profile = profileService.getProfile(profileId);
        if (profile != null) {
            logger.info("Current profile: {}, {}, {}, {}, {}, {}, {}, {},", profile.getUser().getUsername(),profile.getWeightIncreaseType(),
                    profile.getIncreaseWeight(), profile.getIncreaseAtReps(), profile.getWorkoutSelection(),
                    profile.getSelectedTrainingsplan(), profile.getHandleMissingWorkout(), profile.getBodyHeight(),
                    profile.getBmi());
            return ResponseEntity.ok(profileToProfileOutputDtoMapper.map(profile));
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
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long profileId = userDetails.getProfileId();
        
        Profile profile = profileService.getProfile(profileId);
        
        if (profile == null) {
            logger.warn("No profile found with ID: {}", profileId);
            return ResponseEntity.notFound().build();
        }
        
        BodyWeight currentBodyWeight = bodyWeightService.getCurrentBodyWeightForProfile(profile);
        
        if (currentBodyWeight != null) {
            BodyWeightDTO bodyWeightDTO = new BodyWeightDTO();
            bodyWeightDTO.setWeight(currentBodyWeight.getWeight());
            bodyWeightDTO.setDate(currentBodyWeight.getDate().toString());
            logger.info("Current body weight: {}, Date: {}", bodyWeightDTO.getWeight(), bodyWeightDTO.getDate());
            return ResponseEntity.ok(bodyWeightDTO);
        } else {
            logger.warn("No body weight records found for profile ID: {}", profileId);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bodyweight/history")
    public ResponseEntity<List<BodyWeightDTO>> getBodyWeightHistory(
            Authentication authentication, 
            @RequestParam String period) {
        logger.info("Fetching body weight history for period: {}", period);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long profileId = userDetails.getProfileId();
        
        Profile profile = profileService.getProfile(profileId);
        
        if (profile == null) {
            logger.warn("No profile found with ID: {}", profileId);
            return ResponseEntity.notFound().build();
        }
        
        List<BodyWeight> bodyWeightHistory = bodyWeightService.getBodyWeightsByPeriodForProfile(profile, period);
        List<BodyWeightDTO> bodyWeightDTOs = new ArrayList<>();
        
        for (BodyWeight bodyWeight : bodyWeightHistory) {
            BodyWeightDTO dto = new BodyWeightDTO();
            dto.setWeight(bodyWeight.getWeight());
            dto.setDate(bodyWeight.getDate().toString());
            bodyWeightDTOs.add(dto);
        }
        
        logger.info("Found {} body weight records for profile ID: {}", bodyWeightDTOs.size(), profileId);
        return ResponseEntity.ok(bodyWeightDTOs);
    }

    @PostMapping("/bodyweight/add")
    public ResponseEntity<Map<String, String>> addBodyWeight(
            Authentication authentication,
            @RequestBody BodyWeightDTO bodyWeightDTO) {
        logger.info("Adding body weight entry: {}", bodyWeightDTO.getWeight());
        
        // Hole Profile-ID direkt aus CustomUserDetails
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long profileId = userDetails.getProfileId();
        
        Profile profile = profileService.getProfileById(profileId);
        
        if (profile == null) {
            logger.warn("No profile found with ID: {}", profileId);
            return ResponseEntity.notFound().build();
        }
        
        try {
            java.util.Date date = new java.text.SimpleDateFormat("dd.MM.yyyy").parse(bodyWeightDTO.getDate());
            bodyWeightService.saveBodyWeightForProfile(profile, bodyWeightDTO.getWeight(), date);
            
            logger.info("Body weight entry added successfully for profile ID: {}", profileId);
            return ResponseEntity.ok(Map.of("message", "Body weight added successfully"));
        } catch (Exception e) {
            logger.error("Error adding body weight: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("message", "Failed to add body weight: " + e.getMessage()));
        }
    }
}
