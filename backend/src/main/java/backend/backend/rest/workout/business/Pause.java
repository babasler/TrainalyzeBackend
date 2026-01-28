package backend.backend.rest.workout.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pause {
    private boolean isDurationBased;
    private Integer durationMinutes; // optional, not always used in JSON
    private Integer durationSeconds;
}
