package backend.backend.rest.exercise.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
	List<ExerciseEntity> findByUserUsername(String username);

	Optional<ExerciseEntity> findByIdAndUserUsername(Long id, String username);
}
