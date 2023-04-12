package harjoitustyo.reseptit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import harjoitustyo.reseptit.service.UserDetailServiceImpl;
import harjoitustyo.reseptit.domain.AppUser;
import harjoitustyo.reseptit.domain.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	
	private final AppUserRepository repository;

	@Autowired
	public UserDetailServiceImpl(AppUserRepository urepository) {
		this.repository = urepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername: " + username);
		AppUser curruser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}
}
