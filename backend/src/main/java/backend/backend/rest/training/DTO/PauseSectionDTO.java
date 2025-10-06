package backend.backend.rest.training.DTO;

import backend.backend.common.Util.Duration;
import backend.backend.rest.training.section.SectionType;
import jakarta.persistence.Embedded;

public class PauseSectionDTO extends BaseSectionDTO {
    private boolean isDurationPause;
    @Embedded
    private Duration duration;

    public PauseSectionDTO() {
        super(SectionType.PAUSE);
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(String duration) {
        this.duration = Duration.of(duration);
    }

    public boolean isDurationPause() {
        return isDurationPause;
    }

    public void setDurationPause(boolean durationPause) {
        isDurationPause = durationPause;
    }

}
