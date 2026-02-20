package com.arvindcan.studioslocation_backend.mapper;

import com.arvindcan.studioslocation_backend.dto.Feature;
import com.arvindcan.studioslocation_backend.dto.ListingDTO;
import com.arvindcan.studioslocation_backend.dto.PaginatedResponseDTO;
import com.arvindcan.studioslocation_backend.dto.PaginationDTO;
import com.arvindcan.studioslocation_backend.model.Listing;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ListingMapper {
  /**
   * Convert a listing entity to a listing DTO
   *
   * @param listingEntity
   * @return listing DTO
   */
  public ListingDTO toListingReponse(Listing listingEntity) {

    // Create features list
    List<Feature> features = new ArrayList<>();
    if (listingEntity.isHasFurniture()) features.add(Feature.hasFurniture);
    if (listingEntity.isHasAttachedKitchen()) features.add(Feature.hasAttachedKitchen);
    if (listingEntity.isHasAttachedToilet()) features.add(Feature.hasAttachedToilet);

    return new ListingDTO(
        listingEntity.getId(),
        listingEntity.getTitle(),
        listingEntity.getDescription(),
        listingEntity.getRent(),
        listingEntity.getSurface(),
        listingEntity.getCity(),
        listingEntity.getPostalCode(),
        features,
        listingEntity.getCreatedAt(),
        listingEntity.getUpdatedAt());
  }

  /**
   * Converts a page of listing to a PaginatedResponseDTO (with results and pagination info)
   *
   * @param listingsPage Page of Listings (with pagination info)
   * @return An object with listing results and pagination info
   */
  public PaginatedResponseDTO<ListingDTO> toListingsResponse(Page<Listing> listingsPage) {

    // Convert listing entities to listing dto
    List<ListingDTO> listingsDTO = new ArrayList<>();
    listingsPage.getContent().forEach(entity -> listingsDTO.add(toListingReponse(entity)));

    // Create pagination dto
    PaginationDTO paginationDTO =
        new PaginationDTO(
            listingsPage.getNumber(),
            listingsPage.getSize(),
            listingsPage.getTotalElements(),
            listingsPage.getTotalPages());

    return new PaginatedResponseDTO<>(listingsDTO, paginationDTO);
  }
}
