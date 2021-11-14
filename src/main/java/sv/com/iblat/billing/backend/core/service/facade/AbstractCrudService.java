package sv.com.iblat.billing.backend.core.service.facade;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.com.iblat.billing.backend.model.entities.AbstractEntity;

public interface AbstractCrudService<T extends AbstractEntity> {
	JpaRepository<T, Long> getRepository();
	
	default T save(T entity) {
		return getRepository().saveAndFlush(entity);
	}
	
	default void delete(T entity) {
		if(entity == null) {
			throw new EntityNotFoundException();
		}
		getRepository().delete(entity);
	}
	
	default T load(long id) {
		T entity = getRepository().findById(id).orElse(null);
		if(entity == null) {
			throw new EntityNotFoundException();
		}
		
		return entity;
	}
	
	T createNew(T entity);
	
	T find(String parameter);
}
