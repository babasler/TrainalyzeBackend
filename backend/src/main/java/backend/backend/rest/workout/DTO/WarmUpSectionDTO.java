package backend.backend.rest.workout.DTO;

public class WarmUpSectionDTO extends BaseSectionDTO {
    private Long id;
    private float duration;
    private boolean isDurationWarmUp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
    public boolean isDurationWarmUp() {
        return isDurationWarmUp;
    }
}
