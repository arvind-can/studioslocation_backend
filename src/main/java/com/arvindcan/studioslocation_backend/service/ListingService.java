package com.arvindcan.studioslocation_backend.service;

import com.arvindcan.studioslocation_backend.dto.Feature;
import com.arvindcan.studioslocation_backend.dto.ListingResponseDTO;
import com.arvindcan.studioslocation_backend.dto.ListingSearchFiltersDTO;
import com.arvindcan.studioslocation_backend.mapper.ListingMapper;
import com.arvindcan.studioslocation_backend.model.Listing;
import com.arvindcan.studioslocation_backend.repository.ListingRepository;
import com.arvindcan.studioslocation_backend.repository.specifications.ListingSpecifications;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {
    private final ListingRepository listingRepository;
    private final ListingMapper listingMapper;

    public List<ListingResponseDTO> getListings(){
        Iterable<Listing> listingsEntities = listingRepository.findAll();
        List<ListingResponseDTO> listingsDTO = new ArrayList<>();
        listingsEntities.forEach(entity -> listingsDTO.add(listingMapper.toListingReponse(entity)));
        return listingsDTO;
    }

    public List<ListingResponseDTO> getListingsByFilters(@Valid ListingSearchFiltersDTO filtersDto) {
        //Build the chain of specifications based on DTO values
        Specification<Listing> specs = Specification.unrestricted();
        if(filtersDto.city() != null)
            specs = specs.and(ListingSpecifications.hasCity(filtersDto.city()));

        if (filtersDto.postalCode() != null)
            specs = specs.and(ListingSpecifications.hasPostalCode(filtersDto.postalCode()));

        if(filtersDto.minRent() != null)
            specs = specs.and(ListingSpecifications.hasMorePriceThan(filtersDto.minRent()));

        if(filtersDto.maxRent() != null)
            specs = specs.and(ListingSpecifications.hasLessPriceThan(filtersDto.maxRent()));

        if(filtersDto.minSurface() != null)
            specs = specs.and(ListingSpecifications.hasMoreSurfaceThan(filtersDto.minSurface()));

        if(filtersDto.maxSurface() != null)
            specs = specs.and(ListingSpecifications.hasLessSurfaceThan(filtersDto.maxSurface()));

        if(filtersDto.features() != null)
        {
            if(filtersDto.features().contains(Feature.hasFurniture))
                specs = specs.and(ListingSpecifications.isWithFurniture());

            if(filtersDto.features().contains(Feature.hasAttachedToilet))
                specs = specs.and(ListingSpecifications.isWithAttachedToilet());

            if(filtersDto.features().contains(Feature.hasAttachedKitchen))
                specs = specs.and(ListingSpecifications.isWithAttachedKitchen());
        }

        //Query DB and convert resulting entites in DTOs
        Iterable<Listing> listingsEntities =  listingRepository.findAll(specs);
        return listingMapper.toListingsResponse(listingRepository.findAll(specs));

    }
}
