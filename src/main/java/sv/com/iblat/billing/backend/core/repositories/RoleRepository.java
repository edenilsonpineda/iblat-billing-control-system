package sv.com.iblat.billing.backend.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sv.com.iblat.billing.backend.model.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByDescriptionIgnoreCase(String role);
	
	Page<Role> findBy(Pageable pageable);
	
	long countByDescriptionLikeIgnoreCase(String role);
}
