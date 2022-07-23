package com.dokl57.airtravelsapi.service;

import com.dokl57.airtravelsapi.entity.Company;
import com.dokl57.airtravelsapi.entity.Trip;
import com.dokl57.airtravelsapi.exception.ValidationException;
import com.dokl57.airtravelsapi.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip createTrip(UUID companyId, String townFrom, String townTo, LocalDateTime timeIn, LocalDateTime timeOut){
        Optional<Company> company = tripRepository.findCompanyById(companyId);
        if (company.isPresent()) {
            Optional<Trip>  existingTrip = tripRepository.findTripByCompanyIdAndTownFromAndTownTo(companyId, townFrom, townTo);
            if (existingTrip.isPresent()) {
                log.error("Trip with companyId {} and townFrom {} and townTo {} already exists", companyId, townFrom, townTo);
                throw new ValidationException("Trip with companyId " + companyId + " and townFrom " + townFrom + " and townTo " + townTo + " already exists");
            }
            else{
                return tripRepository.save(new Trip(UUID.randomUUID(), townFrom, townTo, timeIn, timeOut, new HashSet<>(), company.get()));
            }
        } else {
            log.error("Company with id {} not found", companyId);
            throw new ValidationException("Company with id " + companyId + " not found");
        }


    }

    public Trip updateTrip(UUID tripId, String townFrom, String townTo, LocalDateTime timeIn, LocalDateTime timeOut){
        Optional<Trip> trip = tripRepository.findTripById(tripId);
        if (trip.isPresent()) {
            trip.get().setTownFrom(townFrom);
            trip.get().setTownTo(townTo);
            trip.get().setTimeIn(timeIn);
            trip.get().setTimeOut(timeOut);
            return tripRepository.save(trip.get());
        } else {
            log.error("Trip with id {} not found", tripId);
            throw new ValidationException("Trip with id " + tripId + " not found");
        }
    }

    public Trip getTripById(UUID id) {
        Optional<Trip> trip = tripRepository.findTripById(id);
        if (trip.isPresent()) {
            return trip.get();
        } else {
            log.error("Trip with id {} not found", id);
            throw new ValidationException("Trip with id " + id + " not found");
        }
    }

    public Iterable<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
}
