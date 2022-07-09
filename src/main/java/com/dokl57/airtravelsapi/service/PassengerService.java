package com.dokl57.airtravelsapi.service;

import com.dokl57.airtravelsapi.entity.PassInTrip;
import com.dokl57.airtravelsapi.entity.Passenger;
import com.dokl57.airtravelsapi.entity.Trip;
import com.dokl57.airtravelsapi.exception.ValidationException;
import com.dokl57.airtravelsapi.repository.PassInTripRepository;
import com.dokl57.airtravelsapi.repository.PassengerRepository;
import com.dokl57.airtravelsapi.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final TripRepository tripRepository;

    private final PassInTripRepository passInTripRepository;

    public PassengerService(PassengerRepository passengerRepository, TripRepository tripRepository, PassInTripRepository passInTripRepository) {
        this.passengerRepository = passengerRepository;
        this.tripRepository = tripRepository;
        this.passInTripRepository = passInTripRepository;
    }

    public Passenger getPassengerByName(String name) {
        Optional<Passenger> passenger = passengerRepository.findPassengerByName(name);
        if (passenger.isPresent()) {
            return passenger.get();
        } else {
            log.error("Passenger with name {} not found", name);
            throw new ValidationException("Passenger with name " + name + " not found");
        }
    }

    public Passenger createPassenger(String name, String surname, String passportNumber, LocalDate dateOfBirth, String phoneNumber) {
        Optional<Passenger> existingPassenger = passengerRepository.findPassengerByPassportNumber(passportNumber);
        if (existingPassenger.isPresent()) {
            log.error("Passenger with passport number {} already exists", passportNumber);
            throw new ValidationException("Passenger with passport number " + passportNumber + " already exists");
        } else {
            return passengerRepository.save(new Passenger(UUID.randomUUID(), name, surname, passportNumber, dateOfBirth, phoneNumber, new HashSet<>()));
        }
    }

    public Passenger updatePassenger(String name, String surname, String passportNumber, LocalDate dateOfBirth, String phoneNumber) {
        Optional<Passenger> existingPassenger = passengerRepository.findPassengerByPassportNumber(passportNumber);
        if (existingPassenger.isPresent()) {
            return passengerRepository.save(new Passenger(existingPassenger.get().getId(), name, surname, passportNumber, dateOfBirth, phoneNumber, new HashSet<>()));
        } else {
            log.error("Passenger with passport number {} not found", passportNumber);
            throw new ValidationException("Passenger with passport number " + passportNumber + " not found");
        }
    }

    public void deletePassenger(String passportNumber) {
        Optional<Passenger> existingPassenger = passengerRepository.findPassengerByPassportNumber(passportNumber);
        if (existingPassenger.isPresent()) {
            passengerRepository.delete(existingPassenger.get());
        } else {
            log.error("Passenger with passport number {} not found", passportNumber);
            throw new ValidationException("Passenger with passport number " + passportNumber + " not found");
        }
    }


    public Iterable<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }


    public Passenger getPassengerByPassportNumber(String passportNumber) {
        Optional<Passenger> passenger = passengerRepository.findPassengerByPassportNumber(passportNumber);
        if (passenger.isPresent()) {
            return passenger.get();
        } else {
            log.error("Passenger with name {} not found", passportNumber);
            throw new ValidationException("Passenger with name " + passportNumber + " not found");
        }
    }

    // add passenger to trip
    public Passenger addPassengerToTrip(String passportNumber, String companyName, String townFrom, String townTo, Integer seatNumber) {
        Optional<Passenger> existingPassenger = passengerRepository.findPassengerByPassportNumber(passportNumber);
        Optional<Trip> existingTrip = tripRepository.findTripByCompanyNameAndTownFromAndTownTo(companyName, townFrom, townTo);
        if (existingTrip.isPresent()) {
            if (existingPassenger.isPresent()) {
                Optional<PassInTrip> existingPassInTrip = passInTripRepository.findPassInTripByPassengerAndTrip(existingPassenger.get(), existingTrip.get());
                if(existingPassInTrip.isPresent()) {
                    log.error("Passenger with passport number {} already exists in trip {}", passportNumber, existingTrip.get().getId());
                    throw new ValidationException("Passenger with passport number " + passportNumber + " already exists in trip " + existingTrip.get().getId());
                } else {
                    existingPassenger.get().getPassInTrips().add(new PassInTrip(UUID.randomUUID(), existingPassenger.get(), existingTrip.get(), seatNumber));
                    return passengerRepository.save(existingPassenger.get());                }
            } else {
                log.error("Passenger with passport number {} not found", passportNumber);
                throw new ValidationException("Passenger with passport number " + passportNumber + " not found");
            }
        } else {
            log.error("Trip with company name {} and town from {} and town to {} not found", companyName, townFrom, townTo);
            throw new ValidationException("Trip with company name " + companyName + " and town from " + townFrom + " and town to " + townTo + " not found");
        }
    }
}
