package backend.backend.rest.profile;

import java.util.Date;
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
    // Prüfe ob der User bereits ein Profil hat
    if (user.getProfile() != null) {
      logger.info("Profile for user {} already exists", username);
      throw new RuntimeException("Profile for this user already exists");
    }
    
    Profile newProfile = new Profile(user, username);
    Profile savedProfile = profileRepository.save(newProfile);
    
    // Wichtig: Setze das Profile auch in der User-Entity (bidirektionale Beziehung)
    user.setProfile(savedProfile);
    
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
      
      logger.info("Profile updated for user: {}", existing.getUser().getUsername());
    } else {
      logger.warn("Profile with ID {} not found", profile.getId());
      throw new RuntimeException("Profile not found");
    }
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
    return new BodyWeight(-1L, 0.0f, new Date(), profile);
  }
  

  private float calculateBmi(float weight, float height) {
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be greater than zero");
    }
    return weight / ((height / 100) * (height / 100));
  }

}
