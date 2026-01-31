package backend.backend.rest.workout.view.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDTO {
    private List<TrainingElementDTO> elements;
}
