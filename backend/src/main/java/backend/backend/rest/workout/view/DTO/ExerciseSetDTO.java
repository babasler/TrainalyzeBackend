package backend.backend.rest.workout.view.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseSetDTO {
    private Integer setNumber;
    private Double weight;
    private Integer repetitions;
    private PauseDTO pause;
}
