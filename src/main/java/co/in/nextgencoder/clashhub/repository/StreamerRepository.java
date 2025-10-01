package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.Streamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Streamer entity.
 */
@Repository
public interface StreamerRepository extends JpaRepository<Streamer, Long> {
}
