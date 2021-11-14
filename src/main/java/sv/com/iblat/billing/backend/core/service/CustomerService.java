package sv.com.iblat.billing.backend.core.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.com.iblat.billing.backend.core.repositories.CustomerRepository;
import sv.com.iblat.billing.backend.core.service.facade.FilterableCrud;
import sv.com.iblat.billing.backend.model.entities.Customer;

@Service
public class CustomerService implements FilterableCrud<Customer> {

	private final CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		// TODO Auto-generated constructor stub
		this.repository = customerRepository;
	}
	
	@Override
	public Customer save(Customer entity) {
		return getRepository().saveAndFlush(entity);
	}
	
	@Override
	public CustomerRepository getRepository() {
		return repository;
	}
	
	@Override
	@Transactional
	public void delete(Customer customerToDelete) {
		FilterableCrud.super.delete(customerToDelete);
	}
	

	@Override
	public Customer find(String name) {
		return getRepository().findByNameIgnoreCase(name);
	}

	@Override
	public Page<Customer> findAnyMatching(Optional<String> filter, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Customer createNew(Customer entity) {
		return new Customer();
	}
	

}
