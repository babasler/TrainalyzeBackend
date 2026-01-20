package backend.backend.rest.exercise.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.common.View.PagedTableResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import backend.backend.rest.exercise.business.ExerciseService;
import backend.backend.rest.exercise.view.DTO.ExerciseCreateRequest;



@RestController
@RequestMapping("/trainalyze/exercise")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @GetMapping()
    public PagedTableResult<ExerciseView> getExercises(@RequestParam int size) {
        return null;
    }

    @GetMapping("/{id}")
    public ExerciseView getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseViewById(id);
    }

    @PostMapping("/create")
    public ExerciseView createExercise(@RequestBody ExerciseCreateRequest request) {
        return exerciseService.createExercise(request);
    }
}
