package backend.backend.rest.workout.view.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupersetPlanDTO {
    private Integer numberOfSets;
    private ExerciseRefDTO firstExercise;
    private ExerciseRefDTO secondExercise;
    private PauseDTO pause;
}
