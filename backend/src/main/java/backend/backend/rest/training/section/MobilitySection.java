package backend.backend.rest.training.section;

import backend.backend.rest.exercise.Exercise;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MobilitySection extends BaseSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Exercise mobilityExercise;
    private int sets;
    private int reps;

    public MobilitySection() {
        super(SectionType.MOBILITY);
    }

    public Exercise getMobilityExercise() {
        return mobilityExercise;
    }

    public void setMobilityExercise(Exercise mobilityExercise) {
        this.mobilityExercise = mobilityExercise;
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
