package sv.com.iblat.billing.backend.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.com.iblat.billing.backend.model.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{

}
