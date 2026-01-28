package backend.backend.rest.workout.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseSet {
    private Integer setNumber;
    private Double weight;
    private Integer repetitions;
    private Pause pause;
}
