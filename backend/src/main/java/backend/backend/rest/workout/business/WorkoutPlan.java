package backend.backend.rest.workout.business;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlan {
    private Long id;
    private String name;
    private List<Section> sections;
}
