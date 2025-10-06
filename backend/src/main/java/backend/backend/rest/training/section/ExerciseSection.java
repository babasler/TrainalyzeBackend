package backend.backend.rest.training.section;
import backend.backend.rest.exercise.Exercise;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ExerciseSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Assuming an ID field for the entity
    @ManyToOne
    private Exercise exercise;
    private int sets;
    private int reps;
    private float weight;
    private PauseSection pauseAfterSet;

    public ExerciseSection() {
        // Default constructor
    }

    public Exercise getExercise() {
        return this.exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getSets() {
        return this.sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return this.reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public PauseSection getPauseAfterSet() {
        return this.pauseAfterSet;
    }

    public void setPauseAfterSet(PauseSection pauseAfterSet) {
        this.pauseAfterSet = pauseAfterSet;
    }

    public ExerciseSection(Exercise exercise, int sets, int reps, float weight, PauseSection pauseAfterSet) {
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.pauseAfterSet = pauseAfterSet;
    }
}
