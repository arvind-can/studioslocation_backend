package com.arvindcan.studioslocation_backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) {

    http.csrf(csrf -> csrf.disable());
    http.authorizeHttpRequests(
        auth -> {
          // Routes publiques
          auth.requestMatchers("/listings/**").permitAll();
          auth.requestMatchers("/users/**").permitAll();

          auth.requestMatchers("/login", "/register").permitAll();

          // Routes privées à l'utilisateur connecté
          auth.requestMatchers("/profile", "/favorites/**").hasRole("USER");

          // Toutes les autres routes doivent être authentifié par défaut
          auth.anyRequest().authenticated();
        });

    // Basic Auth
    http.httpBasic(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  UserDetailsService userDetailsService() {
    UserDetails u1 =
        User.builder()
            .username("Arvind")
            .password(passwordEncoder().encode("waf"))
            .roles("USER")
            .build();
    UserDetails u2 =
        User.builder()
            .username("Viveha")
            .password(passwordEncoder().encode("meow"))
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(u1, u2);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
