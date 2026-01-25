package backend.backend.rest.exercise.mapper;

import backend.backend.rest.exercise.business.Exercise;
import backend.backend.rest.exercise.persistence.ExerciseEntity;
import backend.backend.rest.user.User;

public class ExerciseToExerciseEntityMapper {
    public ExerciseEntity businessToPersistence(Exercise exercise, User user) {
        ExerciseEntity entity = ExerciseEntity.of(
            exercise.getName(),
            exercise.getMuscles(),
            exercise.getWeight(),
            exercise.getRepetitions()
        );
        entity.setUser(user);
        return entity;
    }
}
