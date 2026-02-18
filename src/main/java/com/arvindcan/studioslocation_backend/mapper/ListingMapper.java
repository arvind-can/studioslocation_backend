package com.arvindcan.studioslocation_backend.mapper;

import com.arvindcan.studioslocation_backend.dto.Feature;
import com.arvindcan.studioslocation_backend.dto.ListingResponseDTO;
import com.arvindcan.studioslocation_backend.model.Listing;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListingMapper {
    public ListingResponseDTO toListingReponse(Listing listingEntity){

        //Create features list
        List<Feature> features = new ArrayList<>();
        if(listingEntity.isHasFurniture())
            features.add(Feature.hasFurniture);
        if(listingEntity.isHasAttachedKitchen())
            features.add(Feature.hasAttachedKitchen);
        if(listingEntity.isHasAttachedToilet())
            features.add(Feature.hasAttachedToilet);

        return new ListingResponseDTO(
                listingEntity.getId(),
                listingEntity.getTitle(),
                listingEntity.getDescription(),
                listingEntity.getPrice(),
                listingEntity.getSurface(),
                listingEntity.getCity(),
                listingEntity.getPostalCode(),
                features,
                listingEntity.getCreatedAt(),
                listingEntity.getUpdatedAt()
        );
    }
    
    public List<ListingResponseDTO> toListingsResponse(Iterable<Listing> listingEntities){
        List<ListingResponseDTO> listingsDTO = new ArrayList<>();
        listingEntities.forEach(entity -> listingsDTO.add(toListingReponse(entity)));
        return listingsDTO;
    }
}
