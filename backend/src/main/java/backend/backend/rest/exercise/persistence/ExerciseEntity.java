package backend.backend.rest.exercise.persistence;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExerciseEntity {
    @Id
    @NonNull
    private UUID id;
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

    private ExerciseEntity(UUID id, String name, List<String> muscles, float weight, int repetitions) {
        this.id = id;
        this.name = name;
        this.muscles = muscles;
        this.weight = weight;
        this.repetitions = repetitions;
    }

    public static ExerciseEntity of(String name, List<String> muscles, float weight, int repetitions) {
        return new ExerciseEntity(UUID.randomUUID(), name, muscles, weight, repetitions);
    }
}