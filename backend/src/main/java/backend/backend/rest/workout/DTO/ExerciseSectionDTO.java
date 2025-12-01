package backend.backend.rest.workout.DTO;

public class ExerciseSectionDTO {
    private Long id;
    private String exerciseName;
    private int sets;
    private int repetitions;
    private float weight;
    private Long exerciseId;
    private PauseSectionDTO pauseSection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getExerciseName() {
        return exerciseName;
    }
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }
    public PauseSectionDTO getPauseSection() {
        return pauseSection;
    }
    public void setPauseSection(PauseSectionDTO pauseSection) {
        this.pauseSection = pauseSection;
    }
}
