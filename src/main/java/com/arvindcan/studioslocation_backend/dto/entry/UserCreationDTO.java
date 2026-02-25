package com.arvindcan.studioslocation_backend.dto.entry;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreationDTO(
    @Size(max = 255) @NotBlank(message = "firstname required") String firstname,
    @Size(max = 255) @NotBlank(message = "lastname required") String lastname,
    @Size(max = 255) @Email(message = "mail is not valid") String email,
    @Size(min = 8, max = 255) @NotBlank(message = "password required") String password) {}
