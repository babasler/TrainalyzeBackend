package backend.backend.rest.exercise.mapper;

import backend.backend.rest.exercise.business.Exercise;
import backend.backend.rest.exercise.persistence.ExerciseEntity;

public class ExerciseToExerciseEntityMapper {
    public ExerciseEntity businessToPersistence(Exercise exercise) {
        return new ExerciseEntity(
            exercise.getId(),
            exercise.getName(),
            exercise.getMuscles(),
            exercise.getWeight(),
            exercise.getRepetitions()
        );
    }
}
