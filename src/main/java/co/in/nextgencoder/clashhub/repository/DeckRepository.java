package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Deck entity.
 */
@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
}
