package backend.backend.rest.workout;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
    private final Logger logger = LoggerFactory.getLogger(WorkoutService.class);

    @Autowired
    private WorkoutRepository workoutRepository;

    public String createTraining(Workout workout) {
        logger.info("Received training: {}", workout);
        workoutRepository.save(workout);
        return "Training created successfully";
    }

    public Workout getWorkoutByName(String workoutName) {
        logger.info("Fetching workout by name: {}", workoutName);
        return workoutRepository.findByTrainingName(workoutName).orElse(null);
    }

    public List<Workout> getAllWorkouts() {
        logger.info("Fetching all workouts");
        return workoutRepository.findAll();
    }

    public boolean workoutExists(String workoutName) {
        logger.info("Checking if workout exists: {}", workoutName);
        Optional<Workout> workout = workoutRepository.findByTrainingName(workoutName);
        logger.info("Workout exists: {}", workout.isPresent());
        return workout.isPresent();
    }
}
