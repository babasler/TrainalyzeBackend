package backend.backend.rest.workout.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<WorkoutEntity, Long> {
    List<WorkoutEntity> findByUserUsername(String username);
}
