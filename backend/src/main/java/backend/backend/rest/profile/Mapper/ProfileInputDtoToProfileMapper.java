package backend.backend.rest.profile.Mapper;
import backend.backend.rest.profile.Profile;
import backend.backend.rest.profile.DTO.ProfileInputDTO;



public class ProfileInputDtoToProfileMapper {
    public static Profile map(ProfileInputDTO dto) {
        Profile profile = new Profile();
        profile.setUsername(dto.getUsername());
        profile.setWeightIncreaseType(dto.getWeightIncreaseType());
        profile.setIncreaseWeight(dto.getIncreaseWeight());
        profile.setIncreaseAtReps(dto.getIncreaseAtReps());
        profile.setWorkoutSelection(dto.getWorkoutSelection());
        profile.setSelectedTrainingsplan(dto.getSelectedTrainingsplan());
        profile.setHandleMissingWorkout(dto.getHandleMissingWorkout());
        profile.setBodyHeight(dto.getBodyHeight());
        profile.setBodyWeight(dto.getBodyWeight());
        return profile;
    }
}
