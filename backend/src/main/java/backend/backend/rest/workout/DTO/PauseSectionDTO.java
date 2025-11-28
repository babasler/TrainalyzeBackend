package backend.backend.rest.workout.DTO;

public class PauseSectionDTO extends BaseSectionDTO {
    private String duration;
    private boolean isDurationPause;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isDurationPause() {
        return isDurationPause;
    }

    public void setDurationPause(boolean durationPause) {
        isDurationPause = durationPause;
    }
}