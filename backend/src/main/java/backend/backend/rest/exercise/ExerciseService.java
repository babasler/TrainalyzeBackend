package backend.backend.rest.exercise;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final Logger logger = LoggerFactory.getLogger(ExerciseService.class);
    @Autowired
    private ExerciseRepository exerciseRepository;

    public String createExercise(Exercise exercise) {
        logger.info("Received exercise: {}", exercise);
        exerciseRepository.save(exercise);
        return "Exercise created successfully";
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public boolean exerciseExists(String name) {
        logger.info("Checking if exercise exists: {}", name);
        return exerciseRepository.existsByName(name);
    }
    public Exercise getExerciseByName(String name) {
        logger.info("Fetching exercise by name: {}", name);
        return exerciseRepository.findByName(name);
    }
}
