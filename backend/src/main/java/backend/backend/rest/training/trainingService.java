package backend.backend.rest.training;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class trainingService {
    private final Logger logger = LoggerFactory.getLogger(trainingService.class);

    @Autowired
    private trainingRepository trainingRepository;

    public String createTraining(Training training) {
        logger.info("Received training: {}", training);
        trainingRepository.save(training);
        return "Training created successfully";
    }

    public Training getTrainingByName(String trainingName) {
        logger.info("Fetching training by name: {}", trainingName);
        return trainingRepository.findByTrainingName(trainingName).orElse(null);
    }

    public List<Training> getAllTrainings() {
        logger.info("Fetching all trainings");
        return trainingRepository.findAll();
    }

    public boolean trainingExists(String trainingName) {
        logger.info("Checking if training exists: {}", trainingName);
        Optional<Training> training = trainingRepository.findByTrainingName(trainingName);
        logger.info("Training exists: {}", training.isPresent());
        return training.isPresent();
    }
}
