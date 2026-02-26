package com.arvindcan.studioslocation_backend.repository;

import com.arvindcan.studioslocation_backend.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findUserByEmail(String email);

  User getUserById(int id);

  Optional<User> findUserById(int id);
}
