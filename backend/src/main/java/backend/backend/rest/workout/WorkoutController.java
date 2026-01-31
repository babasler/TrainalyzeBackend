package backend.backend.rest.workout;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.rest.workout.mapper.WorkoutCreateRequestToWorkoutMapper;
import backend.backend.rest.workout.business.Workout;
import backend.backend.rest.workout.view.WorkoutCreateRequest;
import backend.backend.rest.workout.view.WorkoutView;
import backend.backend.rest.workout.business.WorkoutService;

@RestController
@RequestMapping("/trainalyze")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    private final WorkoutCreateRequestToWorkoutMapper requestMapper = new WorkoutCreateRequestToWorkoutMapper();

    @PostMapping("/workouts")
    public ResponseEntity<WorkoutView> createWorkout(@RequestBody WorkoutCreateRequest request) {
        Workout workout = requestMapper.toBusiness(request);
        WorkoutView created = workoutService.createWorkoutForCurrentUser(workout);
        return ResponseEntity.created(URI.create("/trainalyze/workouts/" + created.getId()))
                .body(created);
    }
}
