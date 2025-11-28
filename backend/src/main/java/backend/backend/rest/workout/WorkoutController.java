package backend.backend.rest.workout;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.rest.workout.section.BaseSection;


@RestController
@RequestMapping("/trainalyze/workout")
public class WorkoutController {
    @Autowired
    private final WorkoutService workoutService;
    private final Logger logger = LoggerFactory.getLogger(WorkoutController.class);

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createTraining(@RequestBody Workout workout) {
        for (BaseSection section : workout.getSections()) {
            logger.info("Section: " + section.getClass().getSimpleName());
        }
        return ResponseEntity.ok(Map.of("message", workoutService.createTraining(workout)));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        logger.info("Fetching all workouts");
        List<Workout> workouts = workoutService.getAllWorkouts();
        workouts.forEach(workout -> logger.info("Workout: {}", workout));
        return ResponseEntity.ok(workouts);
    }

    @GetMapping("/get/names")
    public ResponseEntity<Map<String,List<String>>> getAllWorkoutNames() {
        return ResponseEntity.ok(Map.of("workoutNames", workoutService.getAllWorkouts().stream().map(Workout::getWorkoutName).toList()));
    }
    
}
