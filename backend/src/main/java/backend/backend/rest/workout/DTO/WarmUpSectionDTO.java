package backend.backend.rest.workout.DTO;

public class WarmUpSectionDTO extends BaseSectionDTO {
    private Long id;
    private String duration;
    private boolean isDurationWarmUp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    public boolean isDurationWarmUp() {
        return isDurationWarmUp;
    }

    public void setDurationWarmUp(boolean durationWarmUp) {
        this.isDurationWarmUp = durationWarmUp;
    }
}
