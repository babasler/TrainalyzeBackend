package backend.backend.rest.workout.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.backend.rest.exercise.persistence.ExerciseEntity;
import backend.backend.rest.exercise.persistence.ExerciseRepository;
import backend.backend.rest.workout.mapper.WorkoutEntityToWorkoutViewMapper;
import backend.backend.rest.workout.mapper.WorkoutToWorkoutEntityMapper;
import backend.backend.rest.workout.persistence.WorkoutEntity;
import backend.backend.rest.workout.persistence.WorkoutRepository;
import backend.backend.rest.workout.view.WorkoutView;
import backend.backend.rest.user.User;
import backend.backend.rest.user.UserService;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserService userService;

    private final WorkoutToWorkoutEntityMapper toEntityMapper = new WorkoutToWorkoutEntityMapper();
    private final WorkoutEntityToWorkoutViewMapper toViewMapper = new WorkoutEntityToWorkoutViewMapper();

    public WorkoutView createWorkoutForCurrentUser(Workout workout) {
        String username = userService.getCurrentUsername();
        User user = userService.findByUsername(username);

        // Derive exercise associations opportunistically from sections (Superset and ExercisePlan IDs)
        List<Long> ids = new ArrayList<>();
        if (workout.getSections() != null) {
            workout.getSections().forEach(section -> {
                if (section instanceof backend.backend.rest.workout.business.TrainingSection ts && ts.getExercises() != null) {
                    ts.getExercises().forEach(el -> {
                        if (el instanceof backend.backend.rest.workout.business.SupersetPlan sp) {
                            if (sp.getFirstExercise() != null && sp.getFirstExercise().getId() != null) ids.add(sp.getFirstExercise().getId());
                            if (sp.getSecondExercise() != null && sp.getSecondExercise().getId() != null) ids.add(sp.getSecondExercise().getId());
                        } else if (el instanceof backend.backend.rest.workout.business.ExercisePlan ep) {
                            if (ep.getId() != null) ids.add(ep.getId());
                        }
                    });
                }
            });
        }

        List<ExerciseEntity> exercises = ids.isEmpty() ? new ArrayList<>() : exerciseRepository.findAllById(ids).stream()
                .filter(e -> e.getUser() != null && username.equals(e.getUser().getUsername()))
                .collect(Collectors.toList());

        WorkoutEntity entity = toEntityMapper.businessToPersistence(workout, user, exercises);
        WorkoutEntity saved = workoutRepository.save(entity);
        return toViewMapper.toView(saved);
    }
}
