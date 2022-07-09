package com.dokl57.airtravelsapi.repository;

import com.dokl57.airtravelsapi.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {

    Iterable<Trip> findAllByCompany(String company);

    Optional<Trip> findTripByCompanyNameAndTownFromAndTownTo(String companyName, String townFrom, String townTo);
}
