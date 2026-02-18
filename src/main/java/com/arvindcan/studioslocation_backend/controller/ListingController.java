package com.arvindcan.studioslocation_backend.controller;

import com.arvindcan.studioslocation_backend.dto.ListingResponseDTO;
import com.arvindcan.studioslocation_backend.dto.ListingSearchFiltersDTO;
import com.arvindcan.studioslocation_backend.service.ListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class ListingController {

    private final ListingService listingService;

    @GetMapping("/listings")
    public ResponseEntity<List<ListingResponseDTO>> getListings(@Valid @ModelAttribute ListingSearchFiltersDTO filtersDTO){
        return ResponseEntity.ok(listingService.getListingsByFilters(filtersDTO));
    }
}
