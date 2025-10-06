package backend.backend.rest.exercise;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainalyze/exercise")
public class exerciseController {
    private final ExerciseService exerciseService;
    private final Logger logger = LoggerFactory.getLogger(exerciseController.class);

    public exerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise) {
        logger.info("Received exercise: {}", exercise);
        exerciseService.createExercise(exercise);
        logger.info("Exercise saved successfully: {}", exercise.getName());
        return ResponseEntity.ok(Collections.singletonMap("message", "Exercise created successfully"));
    }

    @GetMapping("/get")
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/get/names")
    public List<String> getAllExerciseNames() {
        List<Exercise> exercises = exerciseService.getAllExercises();
        return exercises.stream()
                .map(Exercise::getName)
                .toList();
    }

    @GetMapping("/get/names/{type}")
    public List<String> getExercisesByType(@PathVariable String type) {
        List<Exercise> exercises = exerciseService.getAllExercises();
        return exercises.stream()
                .filter(exercise -> exercise.getType().equalsIgnoreCase(type))
                .map(Exercise::getName)
                .toList();
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> exerciseExists(@RequestBody String name) {
        return ResponseEntity.ok(exerciseService.exerciseExists(name));
    }

}
