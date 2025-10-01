package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.GiveawayEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the GiveawayEntry entity.
 */
@Repository
public interface GiveawayEntryRepository extends JpaRepository<GiveawayEntry, Long> {
}
