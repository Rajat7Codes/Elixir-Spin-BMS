package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.ChallengeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeConfigRepository extends JpaRepository<ChallengeConfig, Long> {
    List<ChallengeConfig> findByChallengeSubTypeIdAndActiveTrue(Long challengeSubTypeId);
}

