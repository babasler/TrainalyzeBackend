package backend.backend.rest.workout.mapper;

import java.util.Collection;

import backend.backend.rest.exercise.persistence.ExerciseEntity;
import backend.backend.rest.user.User;
import backend.backend.rest.workout.business.Workout;
import backend.backend.rest.workout.persistence.WorkoutEntity;

public class WorkoutToWorkoutEntityMapper {
    public WorkoutEntity businessToPersistence(Workout workout, User user, Collection<ExerciseEntity> exercises) {
        WorkoutEntity entity = WorkoutEntity.of(workout.getName());
        entity.setUser(user);
        if (exercises != null) {
            entity.getExercises().addAll(exercises);
        }
        return entity;
    }
}
