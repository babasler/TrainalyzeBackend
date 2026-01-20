package backend.backend.rest.exercise.persistence;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, name = "exercise_name")
    private String name;
    @ElementCollection
    @Column(nullable = false, name = "muscle_groups")
    private List<String> muscles;
    @Column(nullable = false, name = "max_exercise_weight")
    private float weight;
    @Column(nullable = false, name = "max_exercise_repetitions")
    private int repetitions;
    @Column(nullable = false, name = "created_at")
    private Instant createdAt;
    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt;

    protected ExerciseEntity() {}

    private ExerciseEntity(String name, List<String> muscles, float weight, int repetitions) {
        this.name = name;
        this.muscles = muscles;
        this.weight = weight;
        this.repetitions = repetitions;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public static ExerciseEntity of(String name, List<String> muscles, float weight, int repetitions) {
        return new ExerciseEntity(name, muscles, weight, repetitions);
    }
}