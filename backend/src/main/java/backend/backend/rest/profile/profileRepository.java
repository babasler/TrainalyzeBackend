package backend.backend.rest.profile;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface profileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUsername(String username);

    boolean existsByUsername(String username);
}
