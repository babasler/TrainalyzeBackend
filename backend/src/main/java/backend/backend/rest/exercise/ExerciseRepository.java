package backend.backend.rest.exercise;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Exercise findByName(String name);

    Optional<Exercise> findByType(String type);

    boolean existsByName(String name);
}
