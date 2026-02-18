package com.arvindcan.studioslocation_backend.repository;

import com.arvindcan.studioslocation_backend.model.Listing;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends CrudRepository<Listing, Integer>, JpaSpecificationExecutor<Listing> {

}
