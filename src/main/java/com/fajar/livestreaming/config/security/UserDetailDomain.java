package com.fajar.livestreaming.config.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fajar.livestreaming.entities.User;

public class UserDetailDomain implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6884025506545155282L;
	private final User user;

	public UserDetailDomain(User user) {
		this.user = user;
	}
	
	public User getUserDetails () {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 
		return user.getAuthorities().stream().map(auth -> new SimpleGrantedAuthority(auth.getName().toString()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { 
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { 
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { 
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
