package com.arvindcan.studioslocation_backend.service;

import com.arvindcan.studioslocation_backend.configuration.Roles;
import com.arvindcan.studioslocation_backend.errors.runtime.ResourceNotFoundException;
import com.arvindcan.studioslocation_backend.model.User;
import com.arvindcan.studioslocation_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String email) {
    User user =
        repository
            .findUserByEmail(email)
            .orElseThrow(
                () -> new ResourceNotFoundException("User with mail : " + email + " not found"));

    // Since we called User our entity, and Spring also calls user its implementation of userDetails
    // we need to give full path
    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .roles(
            String.valueOf(
                Roles.USER)) // equivalent à .authorities(new SimpleGrantedAuthority("ROLES_USER"))
        .build();
  }

}
