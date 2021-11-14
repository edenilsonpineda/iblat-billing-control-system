package sv.com.iblat.billing.backend.core.service.facade;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sv.com.iblat.billing.backend.model.entities.AbstractEntity;

public interface FilterableCrud<T extends AbstractEntity> extends AbstractCrudService<T> {
	Page<T> findAnyMatching(Optional<String> filter, Pageable pageable);

	long countAnyMatching(Optional<String> filter);
}
