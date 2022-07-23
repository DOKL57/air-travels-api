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


    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
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


}
