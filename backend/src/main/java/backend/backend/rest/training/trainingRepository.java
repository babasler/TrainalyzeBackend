package backend.backend.rest.training;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface trainingRepository extends JpaRepository<Training, Long> {
  Optional<Training> findByTrainingName(String trainingName);

}
