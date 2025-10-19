package backend.backend.rest.profile;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.rest.profile.DTO.ProfileInputDTO;
import backend.backend.rest.profile.DTO.ProfileOutputDTO;
import backend.backend.rest.profile.Mapper.ProfileInputDtoToProfileMapper;
import backend.backend.rest.profile.Mapper.ProfileToProfileOutputDtoMapper;
import backend.backend.rest.user.User;
import backend.backend.rest.user.UserService;

@RestController
@RequestMapping("/trainalyze/profile")
public class profileController {
    private final profileService profileService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(profileController.class);

    public profileController(profileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
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

}
