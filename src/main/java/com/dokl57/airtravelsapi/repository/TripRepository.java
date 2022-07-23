package com.dokl57.airtravelsapi.repository;

import com.dokl57.airtravelsapi.entity.Company;
import com.dokl57.airtravelsapi.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, Long> {

    Iterable<Trip> findAllByCompany(String company);

    Optional<Trip> findTripByCompanyNameAndTownFromAndTownTo(String companyName, String townFrom, String townTo);

    Optional<Trip> findTripByCompanyIdAndTownFromAndTownTo(UUID companyId, String townFrom, String townTo);


    @Query("SELECT c FROM Company c WHERE c.id = :companyId")
    Optional<Company> findCompanyById(UUID companyId);

    Optional<Trip> findTripById(UUID tripId);
}
