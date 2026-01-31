package backend.backend.rest.workout.view.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarmupDTO {
    private boolean isDurationBased;
    private Integer durationMinutes;
    private Integer durationSeconds;
}
