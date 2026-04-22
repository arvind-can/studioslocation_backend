package com.arvindcan.studioslocation_backend.configuration;

import com.arvindcan.studioslocation_backend.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {

  private final CustomUserDetailsService userDetailsService;
  private final JwtFilter jwtFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) {

    http.csrf(csrf -> csrf.disable());
    http.authorizeHttpRequests(
        auth -> {
          // Routes publiques
          auth.requestMatchers("/listings/**").permitAll();
          auth.requestMatchers("/users", "/users/**").permitAll();

          auth.requestMatchers("/login", "/register").permitAll();

          // Routes privées à l'utilisateur connecté
          auth.requestMatchers("/profile", "/favorites/**").hasRole("USER");

          // Toutes les autres routes doivent être authentifié par défaut
          auth.anyRequest().authenticated();
        });

    // Pour navigateur
    /*   http.formLogin(Customizer.withDefaults());*/

    // Basic Auth
    http.httpBasic(Customizer.withDefaults());

    // Stateless (sans sessions)
    http.sessionManagement(
        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
