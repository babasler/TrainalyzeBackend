package backend.backend.rest.workout.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupersetPlan implements TrainingElement {
    private Integer numberOfSets;
    private ExerciseRef firstExercise;
    private ExerciseRef secondExercise;
    private Pause pause;
}
