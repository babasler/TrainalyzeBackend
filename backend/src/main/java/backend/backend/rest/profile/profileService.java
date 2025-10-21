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
    if (profileRepository.existsByUsername(username)) {
      logger.info("Profile with username {} already exists", username);
      throw new RuntimeException("Profile with this username already exists");
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
      
      // Prüfe ob sich das Körpergewicht geändert hat
      boolean bodyWeightChanged = existing.getBodyWeight() != profile.getBodyWeight() 
                                  && profile.getBodyWeight() > 0;
      
      // update the fields
      existing.setUsername(profile.getUsername());
      existing.setWeightIncreaseType(profile.getWeightIncreaseType());
      existing.setIncreaseWeight(profile.getIncreaseWeight());
      existing.setIncreaseAtReps(profile.getIncreaseAtReps());
      existing.setWorkoutSelection(profile.getWorkoutSelection());
      existing.setSelectedTrainingsplan(profile.getSelectedTrainingsplan());
      existing.setHandleMissingWorkout(profile.getHandleMissingWorkout());
      existing.setBodyHeight(profile.getBodyHeight());
      existing.setBodyWeight(profile.getBodyWeight());
      existing.setBmi(calculateBmi(profile.getBodyWeight(), profile.getBodyHeight()));

      // save the updated profile
      profileRepository.save(existing);
      
      // Wenn sich das Körpergewicht geändert hat, speichere einen neuen BodyWeight Eintrag
      if (bodyWeightChanged) {
        bodyWeightService.saveBodyWeightForProfile(existing, profile.getBodyWeight(), new Date());
        logger.info("Saved new BodyWeight entry for profile: {}", existing.getUsername());
      }
    } else {
      logger.warn("Profile with ID {} not found", profile.getId());
      throw new RuntimeException("Profile not found");
    }
    logger.info("Profile updated: {}", profile.getUsername());
  }

  public Profile getProfile(String username) {
    Optional<Profile> profile = profileRepository.findByUsername(username);
    if (profile.isPresent()) {
      logger.info("Returning current profile: {}", profile.get());
      return profile.get();
    } else {
      logger.info("No profiles found");
      return null; 
    }
  }

  public String getProfileId(String username){
    Optional<Profile> profile = profileRepository.findByUsername(username);
    if (profile.isPresent()) {
      logger.info("Returning current profile ID: {}", profile.get().getId());
      return String.valueOf(profile.get().getId());
    } else {
      logger.info("No profiles found for username: {}", username);
      return null;
    }
  }

  public boolean profileExists(String username) {
    return profileRepository.existsByUsername(username);
  }
  
  /**
   * Holt das aktuelle BodyWeight eines Profils
   */
  public BodyWeight getCurrentBodyWeight(String username) {
    Profile profile = getProfile(username);
    if (profile != null) {
      return bodyWeightService.getCurrentBodyWeightForProfile(profile);
    }
    return null;
  }
  
  /**
   * Aktualisiert nur die bodyWeight und BMI Felder ohne ein neues BodyWeight Entry zu erstellen
   * (z.B. wenn manuell ein BodyWeight hinzugefügt wurde)
   */
  public void updateBodyWeightFieldsOnly(@NonNull Profile profile) {
    Optional<Profile> existingProfile = profileRepository.findById(profile.getId());
    if(existingProfile.isPresent()) {
      Profile existing = existingProfile.get();
      existing.setBodyWeight(profile.getBodyWeight());
      existing.setBmi(profile.getBmi());
      profileRepository.save(existing);
      logger.info("Updated bodyWeight fields for profile: {}", existing.getUsername());
    }
  }

  private float calculateBmi(float weight, float height) {
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be greater than zero");
    }
    return weight / ((height / 100) * (height / 100));
  }

}
