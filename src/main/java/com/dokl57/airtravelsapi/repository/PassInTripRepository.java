package com.dokl57.airtravelsapi.repository;

import com.dokl57.airtravelsapi.entity.PassInTrip;
import com.dokl57.airtravelsapi.entity.Passenger;
import com.dokl57.airtravelsapi.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassInTripRepository extends JpaRepository<PassInTrip, Long> {


    Optional<PassInTrip> findPassInTripByPassengerAndTrip(Passenger passenger, Trip trip);
}
