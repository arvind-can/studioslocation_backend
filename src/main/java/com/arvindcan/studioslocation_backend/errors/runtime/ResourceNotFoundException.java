package com.arvindcan.studioslocation_backend.errors.runtime;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
