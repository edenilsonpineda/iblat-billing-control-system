package sv.com.iblat.billing.backend.core.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sv.com.iblat.billing.backend.core.repositories.SupplierRepository;
import sv.com.iblat.billing.backend.core.service.facade.FilterableCrud;
import sv.com.iblat.billing.backend.model.entities.Supplier;

@Service
public class SupplierService implements FilterableCrud<Supplier>{
	
	private final SupplierRepository supplierRepository;
	
	@Autowired
	public SupplierService(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@Override
	public SupplierRepository getRepository() {
		return supplierRepository;
	}

	@Override
	public Supplier createNew(Supplier entity) {
		return new Supplier();
	}
	
	@Override
	public Supplier save(Supplier entity) {
		return getRepository().save(entity);
	}
	
	@Override
	@Transactional
	public void delete(Supplier entity) {
		// TODO Auto-generated method stub
		FilterableCrud.super.delete(entity);
	}

	@Override
	public Supplier find(String contactName) {
		return getRepository().findByContactNameIgnoreCase(contactName);
	}

	@Override
	public Page<Supplier> findAnyMatching(Optional<String> filter, Pageable pageable) {
		return null;
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
