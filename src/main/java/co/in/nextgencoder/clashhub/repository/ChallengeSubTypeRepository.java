package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.ChallengeSubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the ChallengeSubType entity.
 */
@Repository
public interface ChallengeSubTypeRepository extends JpaRepository<ChallengeSubType, Long> {
    List<ChallengeSubType> findByActiveTrue();

    List<ChallengeSubType> findAllByChallengeTypeIdAndActiveTrue(Long id);
}
