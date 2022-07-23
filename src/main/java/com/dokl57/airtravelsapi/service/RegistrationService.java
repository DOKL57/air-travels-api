package com.dokl57.airtravelsapi.service;

import com.dokl57.airtravelsapi.entity.PassInTrip;
import com.dokl57.airtravelsapi.entity.Passenger;
import com.dokl57.airtravelsapi.entity.Trip;
import com.dokl57.airtravelsapi.exception.ValidationException;
import com.dokl57.airtravelsapi.repository.PassInTripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RegistrationService {

    private final PassInTripRepository passInTripRepository;

    public RegistrationService(PassInTripRepository passInTripRepository) {
        this.passInTripRepository = passInTripRepository;
    }

    public PassInTrip registerPassengerToTrip(UUID passengerId, UUID tripId, int seatNumber) {
        Optional<Passenger> existingPassenger = passInTripRepository.findPassengerById(passengerId);
        if (!existingPassenger.isPresent()) {
            log.error("Passenger with id {} not found", passengerId);
            throw new ValidationException("Passenger with id " + passengerId + " not found");
        } else {
            Optional<Trip> existingTrip = passInTripRepository.findTripById(tripId);
            if (!existingTrip.isPresent()) {
                log.error("Trip with id {} not found", tripId);
                throw new ValidationException("Trip with id " + tripId + " not found");
            } else {
                Optional<PassInTrip> existingPassInTrip = passInTripRepository.findPassInTripByPassengerIdAndTripId(passengerId, tripId);
                if (existingPassInTrip.isPresent()) {
                    log.error("PassInTrip with passengerId {} and tripId {} already exists", passengerId, tripId);
                    throw new ValidationException("PassInTrip with passengerId " + passengerId + " and tripId " + tripId + " already exists");
                } else {
                    return passInTripRepository.save(new PassInTrip(UUID.randomUUID(), existingPassenger.get(), existingTrip.get(), seatNumber));
                }
            }

        }

    }
}
