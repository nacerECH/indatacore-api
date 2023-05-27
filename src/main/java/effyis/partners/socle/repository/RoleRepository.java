package effyis.partners.socle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import effyis.partners.socle.entity.Role;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ECHAFAI Nassreddine
 *
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRole(String role);
}
