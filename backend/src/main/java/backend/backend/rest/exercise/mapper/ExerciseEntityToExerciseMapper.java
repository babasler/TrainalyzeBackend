package backend.backend.rest.exercise.mapper;

import backend.backend.rest.exercise.business.Exercise;
import backend.backend.rest.exercise.persistence.ExerciseEntity;

public class ExerciseEntityToExerciseMapper {
    public Exercise entityToBusiness(ExerciseEntity entity) {
        Exercise exercise = new Exercise();
        exercise.setId(entity.getId());
        exercise.setName(entity.getName());
        exercise.setMuscles(entity.getMuscles());
        exercise.setMaxWeight(entity.getWeight());
        exercise.setMaxRepetitions(entity.getRepetitions());
        return exercise;
    }
}
