package com.apibackend.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apibackend.app.repository.IUserDao;

@Service
public class UserService implements UserDetailsService {
	//private Logger logger =(Logger) LogManager.getLogger(UserService.class);
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.apibackend.app.entity.User user=userDao.findByUsername(username);
		if(user==null){
			//logger.error("Error en el login; no existe el usuario '"+username+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login; no existe el usuario '"+username+"' en el sistema!");
		}
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
			//	.peek(authority ->// logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}
	

	@Transactional(readOnly=true)
	public com.apibackend.app.entity.User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
