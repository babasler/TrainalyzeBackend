package backend.backend.rest.workout.DTO;

public class MobilitySectionDTO extends BaseSectionDTO {
    private Long id;
    private long mobilityExerciseId;
    private int sets;
    private int reps;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getMobilityExerciseId() {
        return mobilityExerciseId;
    }
    public void setMobilityExerciseId(long mobilityExerciseId) {
        this.mobilityExerciseId = mobilityExerciseId;
    }
    public int getSets() {
        return sets;
    }
    public void setSets(int sets) {
        this.sets = sets;
    }
    public int getReps() {
        return reps;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }
}