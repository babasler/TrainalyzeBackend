package backend.backend.rest.training;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.rest.training.section.BaseSection;


@RestController
@RequestMapping("/trainalyze/training")
public class trainingController {
    private final trainingService trainingService;
    private final Logger logger = LoggerFactory.getLogger(trainingController.class);

    public trainingController(trainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createTraining(@RequestBody Training training) {
        for (BaseSection section : training.getSections()) {
            logger.info("Section: " + section.getClass().getSimpleName());
        }
        return ResponseEntity.ok(Map.of("message", trainingService.createTraining(training)));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Training>> getAllTraining() {
        logger.info("Fetching all trainings");
        List<Training> trainings = trainingService.getAllTrainings();
        trainings.forEach(training -> logger.info("Training: {}", training));
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/get/names")
    public ResponseEntity<Map<String,List<String>>> getAllTrainingNames() {
        return ResponseEntity.ok(Map.of("trainingNames", trainingService.getAllTrainings().stream().map(Training::getTrainingName).toList()));
    }
    
}
