package backend.backend.rest.workout.persistence;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

import backend.backend.rest.exercise.persistence.ExerciseEntity;
import backend.backend.rest.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "workout_name")
    private String name;

    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    @Column(nullable = false, name = "updated_at")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
        name = "workout_exercises",
        joinColumns = @JoinColumn(name = "workout_id"),
        inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private Set<ExerciseEntity> exercises = new LinkedHashSet<>();

    protected WorkoutEntity() {}

    private WorkoutEntity(String name) {
        this.name = name;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public static WorkoutEntity of(String name) {
        return new WorkoutEntity(name);
    }

    public void touchUpdatedAt() {
        this.updatedAt = Instant.now();
    }
}
