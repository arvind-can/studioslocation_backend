package com.arvindcan.studioslocation_backend.dto;

import java.util.Date;
import java.util.List;

public record ListingDTO(
    int id,
    String title,
    String description,
    int rent,
    int surface,
    String city,
    int postalCode,
    List<Feature> feature,
    Date createdAt,
    Date updatedAt) {}
