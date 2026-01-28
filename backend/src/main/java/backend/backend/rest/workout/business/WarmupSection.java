package backend.backend.rest.workout.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WarmupSection extends Section {
    private boolean isDurationBased;
    private Integer durationMinutes;
    private Integer durationSeconds;

    public WarmupSection(boolean isDurationBased, Integer durationMinutes, Integer durationSeconds) {
        super(SectionType.WARMUP);
        this.isDurationBased = isDurationBased;
        this.durationMinutes = durationMinutes;
        this.durationSeconds = durationSeconds;
    }
}
