package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.TrendingDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TrendingDeck entity.
 */
@Repository
public interface TrendingDeckRepository extends JpaRepository<TrendingDeck, Long> {
}
