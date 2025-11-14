package backend.backend.rest.exercise.Mapper;

import backend.backend.rest.exercise.Exercise;
import backend.backend.rest.exercise.DTO.ExerciseDTO;

public class ExerciseDtoToExerciseMapper {
    public static Exercise map(ExerciseDTO dto) {
        Exercise exercise = new Exercise();
        exercise.setId(dto.getId());
        exercise.setName(dto.getName());
        exercise.setType(dto.getType());
        exercise.setMotionSymmetry(dto.getMotionSymmetry());
        exercise.setMuscleGroups(dto.getMuscleGroups());
        exercise.setEquipment(dto.getEquipment());
        return exercise;
    }
    
}
