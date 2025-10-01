package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Challenge entity.
 */
@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
