package sv.com.iblat.billing.backend.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.com.iblat.billing.backend.model.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	Employee findByNameLikeIgnoreCase(String name);
}
