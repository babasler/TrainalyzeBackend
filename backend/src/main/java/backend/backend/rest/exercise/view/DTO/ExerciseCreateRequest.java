package backend.backend.rest.exercise.view.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseCreateRequest {
    private String name;
    private List<String> muscles;
    private float weight;
    private int repetitions;
}
