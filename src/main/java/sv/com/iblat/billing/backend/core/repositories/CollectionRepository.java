package sv.com.iblat.billing.backend.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.com.iblat.billing.backend.model.entities.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Long>{

}
