package sv.com.iblat.billing.backend.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.com.iblat.billing.backend.model.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	Supplier findByContactNameIgnoreCase(String contactName);
	Supplier findByName(String name);
}
