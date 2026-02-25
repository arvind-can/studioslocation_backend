package com.arvindcan.studioslocation_backend.mapper;

import com.arvindcan.studioslocation_backend.dto.entry.UserCreationDTO;
import com.arvindcan.studioslocation_backend.dto.response.UserDTO;
import com.arvindcan.studioslocation_backend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDTO toResponse(User entity) {
    UserDTO dto =
        new UserDTO(entity.getId(), entity.getFirstname(), entity.getLastname(), entity.getEmail());
    return dto;
  }

  public User toEntity(UserCreationDTO dto) {
    User entity = new User();
    entity.setFirstname(dto.firstname());
    entity.setLastname(dto.lastname());
    entity.setEmail(dto.email());
    entity.setPassword(dto.password());
    return entity;
  }
}
