package backend.backend.rest.workout;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface trainingRepository extends JpaRepository<Workout, Long> {
  Optional<Workout> findByTrainingName(String trainingName);

}
