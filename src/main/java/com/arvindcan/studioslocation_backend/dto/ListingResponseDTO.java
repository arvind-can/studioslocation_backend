package com.arvindcan.studioslocation_backend.dto;


import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public record ListingResponseDTO (
    int id,
    String title,
    String description,
    int price,
    int surface,
    String city,
    int postalCode,
    List<Feature> feature,
    Date createdAt,
    Date updatedAt
){}
