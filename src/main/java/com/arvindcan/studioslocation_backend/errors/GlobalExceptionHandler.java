package com.arvindcan.studioslocation_backend.errors;

import com.arvindcan.studioslocation_backend.errors.runtime.ResourceAlreadyExistsException;
import com.arvindcan.studioslocation_backend.errors.runtime.ResourceNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<String> toHandle(ResourceAlreadyExistsException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> toHandle(ResourceNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  /**
   * Used to handle validation errors on field and object (@Min, @Numberrange etc.)
   *
   * @param ex BindException thrown by spring
   * @return ResponseEntity with status BadRequest and associated errors in the body
   */
  @ExceptionHandler(BindException.class)
  public ResponseEntity<Map<String, String>> handleBindException(BindException ex) {
    Map<String, String> errors = new HashMap<>();
    // Field error(ex @Min)
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    // Object error(ex @NumberRange)
    ex.getBindingResult()
        .getGlobalErrors()
        .forEach(error -> errors.put(error.getObjectName(), error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }
}
