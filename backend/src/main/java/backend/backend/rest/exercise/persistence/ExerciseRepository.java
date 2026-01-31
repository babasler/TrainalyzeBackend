package backend.backend.rest.exercise.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
	List<ExerciseEntity> findByUserUsername(String username);
}
