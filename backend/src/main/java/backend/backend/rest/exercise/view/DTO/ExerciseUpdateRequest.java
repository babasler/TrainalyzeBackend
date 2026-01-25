package backend.backend.rest.exercise.view.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseUpdateRequest {
    private Long id;
    private String newName;
    private String[] newMuscleGroups;
    private float newWeight;
    private int newRepetitions;
}
