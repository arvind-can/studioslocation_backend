package com.arvindcan.studioslocation_backend.controller;

import com.arvindcan.studioslocation_backend.dto.entry.UserCreationDTO;
import com.arvindcan.studioslocation_backend.dto.response.UserDTO;
import com.arvindcan.studioslocation_backend.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserService service;

  @GetMapping("/{id}")
  ResponseEntity<UserDTO> getUserById(@Valid @PathVariable int id) {
    return ResponseEntity.ok(service.getUserById(id));
  }

  @PostMapping
  ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreationDTO userDetails) {
    UserDTO body = service.createUser(userDetails);
    URI location = URI.create("/body/" + body.id());
    return ResponseEntity.created(location).body(body);
  }

  /*  @DeleteMapping
  ResponseEntity<Void> deleteUser(@RequestParam String email) {
    return
  }*/
}
