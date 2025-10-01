package co.in.nextgencoder.clashhub.repository;

import co.in.nextgencoder.clashhub.domain.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Viewer entity.
 */
@Repository
public interface ViewerRepository extends JpaRepository<Viewer, Long> {
}
