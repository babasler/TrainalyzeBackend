package backend.backend.rest.workout;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
  Optional<Workout> findByWorkoutName(String workoutName);

}
