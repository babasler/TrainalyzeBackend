package backend.backend.rest.workout.view;

import java.util.List;

import backend.backend.rest.workout.view.DTO.SectionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutCreateRequest {
    private String name;
    private List<SectionDTO> sections;
}
