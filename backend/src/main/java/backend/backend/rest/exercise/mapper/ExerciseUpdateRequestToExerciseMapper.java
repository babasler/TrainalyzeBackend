package backend.backend.rest.exercise.mapper;

import backend.backend.rest.exercise.business.Exercise;
import backend.backend.rest.exercise.view.DTO.ExerciseUpdateRequest;

public class ExerciseUpdateRequestToExerciseMapper {
    public Exercise dtoToBusiness(ExerciseUpdateRequest request) {
        Exercise exercise = new Exercise();
        exercise.setId(request.getId());
        exercise.setName(request.getNewName());
        exercise.setMuscles(request.getNewMuscleGroups());
        exercise.setMaxWeight(request.getNewWeight());
        exercise.setMaxRepetitions(request.getNewRepetitions());
        return exercise;
    }
}
