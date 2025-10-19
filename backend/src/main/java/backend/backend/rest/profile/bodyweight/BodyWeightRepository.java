package backend.backend.rest.profile.bodyweight;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyWeightRepository extends JpaRepository<BodyWeight, Long> {
    Optional<BodyWeight> findById(Long id);

    BodyWeight findTopByOrderByIdDesc();

    List<BodyWeight> findByDateBetween(Date startDate, Date endDate);

}
