package backend.backend.rest.profile.bodyweight;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.backend.rest.profile.Profile;

public interface BodyWeightRepository extends JpaRepository<BodyWeight, Long> {
    Optional<BodyWeight> findById(Long id);

    BodyWeight findTopByOrderByIdDesc();

    List<BodyWeight> findByDateBetween(Date startDate, Date endDate);
    
    // Finde das neueste BodyWeight für ein spezifisches Profil
    BodyWeight findFirstByProfileOrderByDateDesc(Profile profile);
    
    // Finde alle BodyWeight Einträge für ein Profil
    List<BodyWeight> findByProfileOrderByDateDesc(Profile profile);
    
    // Finde BodyWeight Einträge für ein Profil innerhalb eines Zeitraums
    List<BodyWeight> findByProfileAndDateBetweenOrderByDateDesc(Profile profile, Date startDate, Date endDate);
}
