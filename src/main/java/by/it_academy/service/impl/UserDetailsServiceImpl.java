package by.it_academy.service.impl;

import by.it_academy.bean.User;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.UserServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService providerUserService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user;
		try {
			user = providerUserService.getUser(username);
		} catch (UserServiceException e) {
			throw new UsernameNotFoundException("Error get user", e);
		}
		if (user == null) {
			throw new UsernameNotFoundException("Error get user");
		}
		return new UserDetailsImpl(user);
	}

}
