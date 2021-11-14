package sv.com.iblat.billing.backend.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sv.com.iblat.billing.backend.core.repositories.EmployeeRepository;
import sv.com.iblat.billing.backend.core.service.facade.FilterableCrud;
import sv.com.iblat.billing.backend.model.entities.Employee;

@Service
public class EmployeeService implements FilterableCrud<Employee>{
	
	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeRepository getRepository() {
		return employeeRepository;
	}

	@Override
	public Employee createNew(Employee entity) {
		return new Employee();
	}
	
	@Override
	public Employee save(Employee entity) {
		return getRepository().save(entity);
	}
	
	@Override
	@Transactional
	public void delete(Employee entity) {
		// TODO Auto-generated method stub
		FilterableCrud.super.delete(entity);
	}

	@Override
	public Employee find(String name) {
		return getRepository().findByNameLikeIgnoreCase(name);
	}

	@Override
	public Page<Employee> findAnyMatching(Optional<String> filter, Pageable pageable) {
		return null;
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<Employee> findAll(){
		List<Employee> employeesRawList = new ArrayList<Employee>();
		employeesRawList = getRepository().findAll();
		
		return employeesRawList;
	}

}
