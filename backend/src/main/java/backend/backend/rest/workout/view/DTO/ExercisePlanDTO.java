package backend.backend.rest.workout.view.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExercisePlanDTO {
    private Long id;
    private String name;
    private Double maxWeight;
    private Integer maxRepetitions;
    private List<String> muscleGroups;
    private Integer numberOfSets;
    private List<ExerciseSetDTO> sets;
}
