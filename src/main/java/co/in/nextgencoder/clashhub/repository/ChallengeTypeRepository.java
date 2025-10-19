package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.ChallengeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the ChallengeType entity.
 */
@Repository
public interface ChallengeTypeRepository extends JpaRepository<ChallengeType, Long> {
    List<ChallengeType> findByActiveTrue();
}
