package com.dokl57.airtravelsapi.repository;

import com.dokl57.airtravelsapi.entity.PassInTrip;
import com.dokl57.airtravelsapi.entity.Passenger;
import com.dokl57.airtravelsapi.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PassInTripRepository extends JpaRepository<PassInTrip, Long> {


    Optional<PassInTrip> findPassInTripByPassengerAndTrip(Passenger passenger, Trip trip);

    @Query("SELECT p FROM Passenger p WHERE p.id = :passengerId")
    Optional<Passenger> findPassengerById(UUID passengerId);

    @Query("SELECT t FROM Trip t WHERE t.id = :tripId")
    Optional<Trip> findTripById(UUID tripId);
    Optional<PassInTrip> findPassInTripByPassengerIdAndTripId(UUID passengerId, UUID tripId);
}
