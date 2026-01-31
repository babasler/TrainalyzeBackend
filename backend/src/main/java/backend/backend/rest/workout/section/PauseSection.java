// package backend.backend.rest.workout.section;

// import backend.backend.common.Util.Duration;
// import jakarta.persistence.Embeddable;
// import jakarta.persistence.Embedded;

// @Embeddable
// public class PauseSection extends BaseSection {

//     @Embedded
//     private Duration duration;
//     private boolean isDurationPause;

//     public PauseSection() {
//         super(SectionType.PAUSE);
//     }

//     public Duration getDuration() {
//         return duration;
//     }

//     public void setDuration(Duration duration) {
//         this.duration = duration;
//     }

//     public void setDuration(String duration) {
//         this.duration = Duration.of(duration);
//     }

//     public boolean isDurationPause() {
//         return isDurationPause;
//     }

//     public void setDurationPause(boolean isDurationPause) {
//         // this.isDurationPause = isDurationPause;
//     }

//     @Override
//     public String toString() {
//         return "PauseSection [duration=" + duration + ", isDurationPause=" + isDurationPause + "]";
//     }

// }
