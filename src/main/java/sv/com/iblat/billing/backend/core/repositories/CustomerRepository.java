package sv.com.iblat.billing.backend.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sv.com.iblat.billing.backend.model.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByNameIgnoreCase(String name);
	
	Page<Customer> findBy(Pageable pageable);
}
