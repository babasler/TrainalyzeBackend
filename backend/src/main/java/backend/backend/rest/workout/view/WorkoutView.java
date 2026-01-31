package backend.backend.rest.workout.view;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutView {
    private Long id;
    private String name;
    private List<Long> exerciseIds;
}
