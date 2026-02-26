package com.arvindcan.studioslocation_backend.service;

import com.arvindcan.studioslocation_backend.dto.entry.UserCreationDTO;
import com.arvindcan.studioslocation_backend.dto.response.UserDTO;
import com.arvindcan.studioslocation_backend.errors.runtime.ResourceAlreadyExistsException;
import com.arvindcan.studioslocation_backend.errors.runtime.ResourceNotFoundException;
import com.arvindcan.studioslocation_backend.mapper.UserMapper;
import com.arvindcan.studioslocation_backend.model.User;
import com.arvindcan.studioslocation_backend.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repo;
  private final UserMapper mapper;

  public UserDTO createUser(UserCreationDTO userCreationDTO) {
    if (repo.findUserByEmail(userCreationDTO.email()).isPresent())
      throw new ResourceAlreadyExistsException("User with mail already exists");

    User entity = mapper.toEntity(userCreationDTO);
    return mapper.toResponse(repo.save(entity));
  }

  public UserDTO getUserByEmail(String email) {
    Optional<User> foundEntity = repo.findUserByEmail(email);
    if (foundEntity.isEmpty())
      throw new ResourceNotFoundException("User with mail : " + email + " not found");

    return mapper.toResponse(foundEntity.get());
  }

  public UserDTO getUserById(int id) {
    Optional<User> foundEntity = repo.findUserById(id);
    if (foundEntity.isEmpty())
      throw new ResourceNotFoundException("User with id : " + id + " not found");

    return mapper.toResponse(foundEntity.get());
  }
}
