package backend.backend.rest.training.section;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TrainingSection extends BaseSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ExerciseSection> exerciseSections;

    public TrainingSection() {
        super(SectionType.TRAINING);
        exerciseSections = new ArrayList<>();
    }

    public List<ExerciseSection> getExerciseSections() {
        return exerciseSections;
    }

    public void addExercise(ExerciseSection exerciseSection) {
        this.exerciseSections.add(exerciseSection);
    }

    public void removeExercise(ExerciseSection exerciseSection) {
        this.exerciseSections.remove(exerciseSection);
    }

    public ExerciseSection getExerciseSection(int index) {
        if (index < 0 || index >= exerciseSections.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return exerciseSections.get(index);
    }
}