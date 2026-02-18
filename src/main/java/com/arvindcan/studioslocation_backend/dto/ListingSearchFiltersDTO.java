package com.arvindcan.studioslocation_backend.dto;
import com.arvindcan.studioslocation_backend.dto.validator.numberrange.NumberRange;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

@NumberRange(minField = "minRent", maxField = "maxRent", message = "Le loyer min devrait être inférieur au loyer max")
@NumberRange(minField = "minSurface", maxField = "maxSurface", message = "La surface min devrait être inférieur à la surface max")
public record ListingSearchFiltersDTO(
        @Size(max = 255)
        String city,
         @Min(0)
         Integer postalCode,
         @Min(0)
         Integer minRent,
         @Min(0)
         Integer maxRent,
         @Min(0)
         Integer minSurface,
         @Min(0)
         Integer maxSurface,
         List<Feature> features
) {}
