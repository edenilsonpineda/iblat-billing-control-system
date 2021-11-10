package sv.com.iblat.billing.backend.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sv.com.iblat.billing.backend.core.repositories.UserRepository;
import sv.com.iblat.billing.backend.model.dto.MyUserDetails;
import sv.com.iblat.billing.backend.model.entities.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserNameIgnoreCase(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user, please try again!");
		} else {
			return new MyUserDetails(user);
		}
	}

}
