package backend.backend.rest.workout.view.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingElementDTO {
    public enum Type { EXERCISE_PLAN, SUPERSET_PLAN }
    private Type type;
    private ExercisePlanDTO exercisePlan; // present when type == EXERCISE_PLAN
    private SupersetPlanDTO supersetPlan; // present when type == SUPERSET_PLAN
}
