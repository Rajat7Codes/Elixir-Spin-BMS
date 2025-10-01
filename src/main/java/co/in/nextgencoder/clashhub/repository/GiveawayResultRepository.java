package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.GiveawayResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the GiveawayResult entity.
 */
@Repository
public interface GiveawayResultRepository extends JpaRepository<GiveawayResult, Long> {
}
