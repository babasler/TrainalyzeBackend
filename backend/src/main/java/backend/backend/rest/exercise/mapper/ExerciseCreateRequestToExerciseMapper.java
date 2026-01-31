package backend.backend.rest.exercise.mapper;

import backend.backend.rest.exercise.business.Exercise;
import backend.backend.rest.exercise.view.DTO.ExerciseCreateRequest;

public class ExerciseCreateRequestToExerciseMapper {
    public Exercise dtoToBusiness(ExerciseCreateRequest request) {
        Exercise exercise = new Exercise();
        exercise.setName(request.getName());
        exercise.setMuscles(request.getMuscleGroups());
        exercise.setWeight(request.getWeight());
        exercise.setRepetitions(request.getRepetitions());
        return exercise;
    }
}
