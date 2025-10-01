package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Card entity.
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
