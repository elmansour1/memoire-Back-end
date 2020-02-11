/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensp.agem.security.services;

import com.ensp.agem.data.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author mansour
 */
public class UserDetailsImpl implements UserDetails{

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;
    
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
    
    public static UserDetailsImpl build(Utilisateur user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
            return password;
    }

    @Override
    public String getUsername() {
            return username;
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

    @Override
    public boolean equals(Object o) {
            if (this == o)
                    return true;
            if (o == null || getClass() != o.getClass())
                    return false;
            UserDetailsImpl user = (UserDetailsImpl) o;
            return Objects.equals(id, user.id);
    }
    
}
