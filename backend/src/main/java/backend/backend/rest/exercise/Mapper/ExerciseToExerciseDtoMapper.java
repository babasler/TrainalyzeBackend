package backend.backend.rest.exercise.Mapper;

import backend.backend.rest.exercise.Exercise;
import backend.backend.rest.exercise.DTO.ExerciseDTO;

public class ExerciseToExerciseDtoMapper {
    public static ExerciseDTO map(Exercise exercise) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        dto.setType(exercise.getType());
        dto.setMotionSymmetry(exercise.getMotionSymmetry());
        dto.setMuscleGroups(exercise.getMuscleGroups());
        dto.setEquipment(exercise.getEquipment());
        return dto;
    }
}
