package backend.backend.rest.exercise.mapper;

import backend.backend.rest.exercise.business.Exercise;
import backend.backend.rest.exercise.view.DTO.ExerciseCreateRequest;

public class ExerciseCreateRequestToExerciseMapper {
    public Exercise dtoToBusiness(ExerciseCreateRequest request) {
        Exercise exercise = new Exercise();
        exercise.setName(request.getName());
        exercise.setMuscles(request.getMuscleGroups());
        exercise.setMaxWeight(request.getWeight());
        exercise.setMaxRepetitions(request.getRepetitions());
        return exercise;
    }
}
