package backend.backend.rest.training.DTO;

public class ExerciseSectionDTO {
    //private Exercise exercise;
    private String exercise;
    private int sets;
    private int reps;
    private float weight;
    private PauseSectionDTO pauseAfterSet;

    public ExerciseSectionDTO(String exercise, int sets, int reps, float weight, PauseSectionDTO pauseAfterSet) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.pauseAfterSet = pauseAfterSet;
    }

    public ExerciseSectionDTO() {
        this.exercise = "";
        this.sets = 0;
        this.reps = 0;
        this.weight = 0;
        this.pauseAfterSet = null;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public PauseSectionDTO getPauseAfterSet() {
        return pauseAfterSet;
    }

    public void setPauseAfterSet(PauseSectionDTO pauseAfterSet) {
        this.pauseAfterSet = pauseAfterSet;
    }
}
