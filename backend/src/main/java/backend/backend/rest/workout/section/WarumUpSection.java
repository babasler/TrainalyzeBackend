package backend.backend.rest.workout.section;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WarumUpSection extends BaseSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float duration;
    private boolean isDurationWarmUp;

    public WarumUpSection() {
        // Default constructor for JPA
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public WarumUpSection(float duration, boolean isDurationWarmUp) {
        this.duration = duration;
        this.isDurationWarmUp = isDurationWarmUp;
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

    public void setDurationWarmUp(boolean durationWarmUp) {
        isDurationWarmUp = durationWarmUp;
    }

    @Override
    public String toString() {
        return "WarumUpSection [duration=" + duration + ", isDurationWarmUp=" + isDurationWarmUp + "]";
    }
}
