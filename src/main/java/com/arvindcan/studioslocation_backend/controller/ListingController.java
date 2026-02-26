package com.arvindcan.studioslocation_backend.controller;

import com.arvindcan.studioslocation_backend.dto.entry.ListingSearchFiltersDTO;
import com.arvindcan.studioslocation_backend.dto.response.ListingDTO;
import com.arvindcan.studioslocation_backend.dto.response.PaginatedResponseDTO;
import com.arvindcan.studioslocation_backend.service.ListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class ListingController {

  private final ListingService listingService;

  @GetMapping("/listings")
  public ResponseEntity<PaginatedResponseDTO<ListingDTO>> getListings(
      @RequestParam(defaultValue = "0") int pageNo,
      @RequestParam(defaultValue = "1") int pageSize,
      @Valid @ModelAttribute ListingSearchFiltersDTO filtersDTO) {
    return ResponseEntity.ok(listingService.getListingsByFilters(pageNo, pageSize, filtersDTO));
  }

  @GetMapping("/profile")
  public ResponseEntity<String> greet() {
    return ResponseEntity.ok("hello profile user");
  }

  @GetMapping("/profiles")
  public ResponseEntity<String> greeting() {
    return ResponseEntity.ok("hello all auth user");
  }
}
