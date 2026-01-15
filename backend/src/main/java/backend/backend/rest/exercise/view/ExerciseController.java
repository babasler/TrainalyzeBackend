package backend.backend.rest.exercise.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.common.View.PagedTableResult;


@RestController
@RequestMapping("/trainalyze/exercise")
public class ExerciseController {
    @GetMapping()
    public PagedTableResult<ExerciseView> getExercises(@RequestParam int size) {
        return null;
    }
    
}
