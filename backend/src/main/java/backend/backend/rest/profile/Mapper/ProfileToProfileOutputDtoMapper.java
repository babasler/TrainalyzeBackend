package backend.backend.rest.profile.Mapper;

import backend.backend.rest.profile.Profile;
import backend.backend.rest.profile.DTO.ProfileOutputDTO;

public class ProfileToProfileOutputDtoMapper {
    public static ProfileOutputDTO map(Profile profile) {
        ProfileOutputDTO dto = new ProfileOutputDTO();
        dto.setProfileId(profile.getId());
        dto.setWeightIncreaseType(profile.getWeightIncreaseType());
        dto.setIncreaseWeight(profile.getIncreaseWeight());
        dto.setIncreaseAtReps(profile.getIncreaseAtReps());
        dto.setWorkoutSelection(profile.getWorkoutSelection());
        dto.setSelectedTrainingsplan(profile.getSelectedTrainingsplan());
        dto.setHandleMissingWorkout(profile.getHandleMissingWorkout());
        dto.setBodyHeight(profile.getBodyHeight());
        dto.setBmi(profile.getBmi());
        return dto;
    }
    
}
