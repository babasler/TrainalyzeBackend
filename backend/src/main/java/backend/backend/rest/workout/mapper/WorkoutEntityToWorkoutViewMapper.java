package backend.backend.rest.workout.mapper;

import java.util.List;
import java.util.stream.Collectors;

import backend.backend.rest.workout.persistence.WorkoutEntity;
import backend.backend.rest.workout.view.WorkoutView;

public class WorkoutEntityToWorkoutViewMapper {
    public WorkoutView toView(WorkoutEntity entity) {
        List<Long> exerciseIds = entity.getExercises().stream()
                .map(e -> (Long) e.getId())
                .collect(Collectors.toList());
        return new WorkoutView(entity.getId(), entity.getName(), exerciseIds);
    }
}
