package backend.backend.rest.workout.DTO;

public class PauseSectionDTO extends BaseSectionDTO {
    private float duration;
    private boolean isDurationPause;

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public boolean isDurationPause() {
        return isDurationPause;
    }

    public void setDurationPause(boolean durationPause) {
        isDurationPause = durationPause;
    }
}