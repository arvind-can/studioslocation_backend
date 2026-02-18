package com.arvindcan.studioslocation_backend.repository.specifications;

import com.arvindcan.studioslocation_backend.model.Listing;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class ListingSpecifications {

    //No lambda for clarity
    //Create an implementation of the functional interface Specification
    //With toPredicate returning a predicate
    public static Specification<Listing> hasCity(String city){
        return new Specification<Listing>() {
            @Override
            public @Nullable Predicate toPredicate(Root<Listing> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                 return criteriaBuilder.equal(criteriaBuilder.lower(root.get("city")), city.toLowerCase());
            }
        };
    }

    public static Specification<Listing> hasPostalCode(Integer postalCode) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("postalCode"), postalCode);
    }

    public static Specification<Listing> hasMorePriceThan(Integer priceMin) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceMin);
    }

    public static Specification<Listing> hasLessPriceThan(Integer priceMax) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceMax);
    }

    public static Specification<Listing> hasMoreSurfaceThan(Integer surfaceMin) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("surface"), surfaceMin);
    }

    public static Specification<Listing> hasLessSurfaceThan(Integer surfaceMax) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("surface"), surfaceMax);
    }

    public static Specification<Listing> isWithFurniture(){
        return (root, query, criteriaBuilder) ->  criteriaBuilder.isTrue(root.get("hasFurniture"));
    }

    public static Specification<Listing> isWithAttachedToilet(){
        return (root, query, criteriaBuilder) ->  criteriaBuilder.isTrue(root.get("hasAttachedToilet"));
    }

    public static Specification<Listing> isWithAttachedKitchen(){
        return (root, query, criteriaBuilder) ->  criteriaBuilder.isTrue(root.get("hasAttachedKitchen"));
    }


}


