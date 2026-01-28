package backend.backend.rest.workout.business;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrainingSection extends Section {
    private List<TrainingElement> exercises; // can be ExercisePlan or SupersetPlan

    public TrainingSection(List<TrainingElement> exercises) {
        super(SectionType.TRAINING);
        this.exercises = exercises;
    }
}
