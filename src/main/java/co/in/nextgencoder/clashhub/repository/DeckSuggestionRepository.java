package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.DeckSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeckSuggestion entity.
 */
@Repository
public interface DeckSuggestionRepository extends JpaRepository<DeckSuggestion, Long> {
}
