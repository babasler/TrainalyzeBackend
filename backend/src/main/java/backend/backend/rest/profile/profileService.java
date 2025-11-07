package backend.backend.rest.profile;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.rest.profile.bodyweight.BodyWeight;
import backend.backend.rest.profile.bodyweight.BodyWeightService;
import backend.backend.rest.user.User;
import lombok.NonNull;

@Service
public class profileService {
  private final Logger logger = LoggerFactory.getLogger(profileService.class);
  
  @Autowired
  private profileRepository profileRepository;
  
  @Autowired
  private BodyWeightService bodyWeightService;

  public void createProfile(@NonNull User user, @NonNull String username) {
    if (profileRepository.findById(user.getProfile().getId()).isPresent()) {
      logger.info("Profile with user ID {} already exists", user.getProfile().getId());
      throw new RuntimeException("Profile with this user ID already exists");
    }
    profileRepository.save(new Profile(user, username));
    logger.info("Profile created: {}", username);
  }

  public void updateProfile(@NonNull Profile profile) {
    // fetch the existing profile
    Optional<Profile> existingProfile = profileRepository.findById(profile.getId());
    logger.info("Found Profile: {}", existingProfile);
    if(existingProfile.isPresent()) {
      Profile existing = existingProfile.get();
      
      
      // update the fields
      existing.setWeightIncreaseType(profile.getWeightIncreaseType());
      existing.setIncreaseWeight(profile.getIncreaseWeight());
      existing.setIncreaseAtReps(profile.getIncreaseAtReps());
      existing.setWorkoutSelection(profile.getWorkoutSelection());
      existing.setSelectedTrainingsplan(profile.getSelectedTrainingsplan());
      existing.setHandleMissingWorkout(profile.getHandleMissingWorkout());
      existing.setBodyHeight(profile.getBodyHeight());
      existing.setBmi(calculateBmi(getCurrentBodyWeight(profile).getWeight(), profile.getBodyHeight()));

      // save the updated profile
      profileRepository.save(existing);
      
    } else {
      logger.warn("Profile with ID {} not found", profile.getId());
      throw new RuntimeException("Profile not found");
    }
    logger.info("Profile updated: {}", profile.getUsername());
  }

  public Profile getProfile(Long profileId) {
    Optional<Profile> profile = profileRepository.findById(profileId);
    if (profile.isPresent()) {
      logger.info("Returning current profile: {}", profile.get());
      return profile.get();
    } else {
      logger.info("No profiles found");
      return null; 
    }
  }

  /**
   * Alias für getProfile - für bessere Lesbarkeit
   */
  public Profile getProfileById(Long profileId) {
    return getProfile(profileId);
  }

  
  /**
   * Holt das aktuelle BodyWeight eines Profils
   */
  public BodyWeight getCurrentBodyWeight(Profile profile) {
    if (profile != null) {
      return bodyWeightService.getCurrentBodyWeightForProfile(profile);
    }
    return null;
  }
  

  private float calculateBmi(float weight, float height) {
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be greater than zero");
    }
    return weight / ((height / 100) * (height / 100));
  }

}
