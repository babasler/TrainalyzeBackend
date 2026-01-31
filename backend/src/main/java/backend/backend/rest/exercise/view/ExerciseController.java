package backend.backend.rest.exercise.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backend.backend.rest.exercise.business.ExerciseService;
import backend.backend.rest.exercise.view.DTO.ExerciseCreateRequest;
import backend.backend.rest.exercise.view.DTO.ExerciseUpdateRequest;
import backend.backend.rest.exercise.mapper.ExerciseCreateRequestToExerciseMapper;
import backend.backend.rest.exercise.mapper.ExerciseToExerciseViewMapper;
import backend.backend.rest.exercise.mapper.ExerciseUpdateRequestToExerciseMapper;
import backend.backend.rest.exercise.business.Exercise;
import java.util.List;



@RestController
@RequestMapping("/trainalyze/exercise")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);

    @GetMapping()
    public ExerciseView[] getExercises() {
        logger.info("Fetching exercises for current user");
        List<Exercise> exercises = exerciseService.getCurrentUserExercises();
        ExerciseToExerciseViewMapper toView = new ExerciseToExerciseViewMapper();
        ExerciseView[] rows = exercises.stream().map(toView::businessToView).toArray(ExerciseView[]::new);
        return rows;
    }

    @GetMapping("/{id}")
    public ExerciseView getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseByIdForCurrentUser(id);
        if (exercise == null) {
            return null;
        }
        ExerciseToExerciseViewMapper toView = new ExerciseToExerciseViewMapper();
        return toView.businessToView(exercise);
    }

    @PostMapping("/create")
    public ExerciseView createExercise(@RequestBody ExerciseCreateRequest request) {
        logger.info("Creating new exercise: {}, {}, {}", request.getName(), request.getWeight(), request.getRepetitions());
        ExerciseCreateRequestToExerciseMapper toBiz = new ExerciseCreateRequestToExerciseMapper();
        Exercise created = exerciseService.createExerciseForCurrentUser(toBiz.dtoToBusiness(request));
        ExerciseToExerciseViewMapper toView = new ExerciseToExerciseViewMapper();
        return toView.businessToView(created);
    }

    @PutMapping("/{id}")
    public ExerciseView updateExercise(@PathVariable Long id, @RequestBody ExerciseUpdateRequest request) {
        ExerciseUpdateRequestToExerciseMapper toBiz = new ExerciseUpdateRequestToExerciseMapper();
        Exercise ex = toBiz.dtoToBusiness(request);
        if (ex.getId() != null && !id.equals(ex.getId())) {
            throw new IllegalArgumentException("Path variable id does not match request body id.");
        }
        //Hier muss das richtige Entity geholt und geupdated werden.
        logger.info("Updating exercise id {}: {}, {}, {}", ex.getId(), ex.getName(), ex.getWeight(), ex.getRepetitions());
        Exercise updated = exerciseService.updateExerciseForCurrentUser(ex);
        ExerciseToExerciseViewMapper toView = new ExerciseToExerciseViewMapper();
        return toView.businessToView(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExerciseForCurrentUser(id);
    }
}
