package backend.backend.rest.workout.view.DTO;


import backend.backend.rest.workout.business.SectionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {
    private SectionType sectionType;
    private WarmupDTO warmup; // used when sectionType == WARMUP
    private TrainingDTO training; // used when sectionType == TRAINING
}
