package com.expense.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.expense.entity.Role;
import com.expense.entity.user;
import com.expense.login.dto.UserRegistrationDto;
import com.expense.repository.ExpenseRepository;
import com.expense.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	
	 @Autowired
	 private UserRepository userrepo;

	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	 
	public UserServiceImpl(UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}

	@Override
	public user save(UserRegistrationDto u) {
		user user= new user(u.getFirstName(), u.getLastName(), u.getEmail(), passwordEncoder.encode(u.getPassword()), Arrays.asList(new Role("Role_User")));
		// TODO Auto-generated method stub
		return userrepo .save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		user u= userrepo.findByEmail(username);
		if(u == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), mapRolesToAuthorities(u.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	
	
}
