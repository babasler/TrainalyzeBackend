package backend.backend.rest.profile.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import backend.backend.rest.profile.Profile;
import backend.backend.rest.profile.DTO.ProfileOutputDTO;
import backend.backend.rest.profile.bodyweight.BodyWeightService;

@Component
public class ProfileToProfileOutputDtoMapper {
    @Autowired
    BodyWeightService bodyWeightService;


    public ProfileOutputDTO map(Profile profile) {
        ProfileOutputDTO dto = new ProfileOutputDTO();
        dto.setProfileId(profile.getId());
        dto.setWeightIncreaseType(profile.getWeightIncreaseType());
        dto.setIncreaseWeight(profile.getIncreaseWeight());
        dto.setIncreaseAtReps(profile.getIncreaseAtReps());
        dto.setWorkoutSelection(profile.getWorkoutSelection());
        dto.setSelectedTrainingsplan(profile.getSelectedTrainingsplan());
        dto.setHandleMissingWorkout(profile.getHandleMissingWorkout());
        dto.setBodyHeight(profile.getBodyHeight());
        
        // Hole aktuelles BodyWeight, falls vorhanden
        var currentBodyWeight = bodyWeightService.getCurrentBodyWeightForProfile(profile);
        if (currentBodyWeight != null) {
            dto.setBodyWeight(currentBodyWeight.getWeight());
        } else {
            dto.setBodyWeight(0.0f);
        }
        
        dto.setBmi(profile.getBmi());
        return dto;
    }
    
}
