package sv.com.iblat.billing.backend.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sv.com.iblat.billing.backend.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserNameIgnoreCase(String userName);

	User findByEmailIgnoreCase(String email);

	Page<User> findBy(Pageable pageable);

	Page<User> findByEmailLikeIgnoreCaseOrUserNameLikeIgnoreCase(
			String emailLike, String userName, Pageable pageable);

	long countByEmailLikeIgnoreCaseOrUserNameLikeIgnoreCase(
			String emailLike, String userName);
}
