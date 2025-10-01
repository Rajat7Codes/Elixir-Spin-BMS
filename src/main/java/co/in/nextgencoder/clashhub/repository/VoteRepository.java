package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Vote entity.
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
