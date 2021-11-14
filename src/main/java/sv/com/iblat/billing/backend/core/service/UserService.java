package sv.com.iblat.billing.backend.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import sv.com.iblat.billing.backend.core.repositories.UserRepository;
import sv.com.iblat.billing.backend.core.service.facade.FilterableCrudService;
import sv.com.iblat.billing.backend.model.dto.UserInformationDto;
import sv.com.iblat.billing.backend.model.entities.User;
import sv.com.iblat.billing.backend.util.UserFriendlyDataException;

@Slf4j
@Service
public class UserService implements FilterableCrudService<User> {

	public static final String MODIFY_LOCKED_USER_NOT_PERMITTED = "User has been locked and cannot be modified or deleted";
	private static final String DELETING_SELF_NOT_PERMITTED = "You cannot delete your own account";
	private final UserRepository userRepository;

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Page<User> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByEmailLikeIgnoreCaseOrUserNameLikeIgnoreCase(repositoryFilter, repositoryFilter,
					pageable);
		} else {
			return find(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return userRepository.countByEmailLikeIgnoreCaseOrUserNameLikeIgnoreCase(repositoryFilter,
					repositoryFilter);
		} else {
			return count();
		}
	}

	@Override
	public UserRepository getRepository() {
		return userRepository;
	}

	public Page<User> find(Pageable pageable) {
		return getRepository().findBy(pageable);
	}

	@Override
	public User save(User currentUser, User entity) {
		log.info("Saving new user");
		throwIfUserLocked(entity);
		User newUser = new User();
		
		newUser.setRoles(entity.getRoles());
		newUser.setUserName(entity.getUserName());
		newUser.setEmail(entity.getEmail());
		newUser.setPassword(passwordEncoder.encode(entity.getPassword()));
		
		
		return getRepository().saveAndFlush(newUser);
	}

	@Override
	@Transactional
	public void delete(User currentUser, User userToDelete) {
		throwIfDeletingSelf(currentUser, userToDelete);
		throwIfUserLocked(userToDelete);
		FilterableCrudService.super.delete(currentUser, userToDelete);
	}

	private void throwIfDeletingSelf(User currentUser, User user) {
		if (currentUser.equals(user)) {
			throw new UserFriendlyDataException(DELETING_SELF_NOT_PERMITTED);
		}
	}

	private void throwIfUserLocked(User entity) {
		if (entity != null && entity.isEnabled()) {
			throw new UserFriendlyDataException(MODIFY_LOCKED_USER_NOT_PERMITTED);
		}
	}

	@Override
	public User createNew(User currentUser) {
		return new User();
	}

	@Override
	public User find(String userName) {
		return getRepository().findByUserNameIgnoreCase(userName);
	}

	public List<UserInformationDto> findAll() {
		List<User> usersRawList = getRepository().findAll();
		
		List<UserInformationDto> usersDto = new ArrayList<UserInformationDto>();
		
		if (!usersRawList.isEmpty() && usersRawList.size() > 0) {
			usersRawList.stream().forEach(u -> {
				usersDto.add(UserInformationDto.builder().userId(u.getId()).userName(u.getUserName()).email(u.getEmail())
						.roles(u.getRoles()).creationDate(u.getCreatedAt()).build());

			});
		}
		return usersDto;
	}

	public UserInformationDto findUser(String userName) {
		UserInformationDto response = null;

		User user = new User();
		user = getRepository().findByUserNameIgnoreCase(userName);

		if (Objects.nonNull(user)) {
			response = UserInformationDto.builder().email(user.getEmail()).creationDate(user.getCreatedAt())
					.userName(user.getUserName()).build();
		} else {
			throw new UserFriendlyDataException(String.format("The requested username %1s is not found ", userName));
		}

		return response;

	}

}
