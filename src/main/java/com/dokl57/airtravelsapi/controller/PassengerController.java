package com.dokl57.airtravelsapi.controller;

import com.dokl57.airtravelsapi.dto.PassengerDto;
import com.dokl57.airtravelsapi.dto.TripDto;
import com.dokl57.airtravelsapi.entity.Passenger;
import com.dokl57.airtravelsapi.entity.Trip;
import com.dokl57.airtravelsapi.service.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    /*
        POST /api/passengers/create - create new passenger
     */

    @PostMapping(value = "/create")
    Passenger createPassenger(@RequestBody @NotNull PassengerDto dto) {
        log.info("Creating new passenger with name {}", dto.getName());
        return passengerService.createPassenger(dto.getName(), dto.getSurname(), dto.getPassportNumber(), dto.getDateOfBirth(), dto.getPhoneNumber());
    }

    /*
        DELETE api/passengers/delete/{passportNumber} - delete passenger by name
     */
    @DeleteMapping(value = "/delete/{passportNumber}")
    void deletePassenger(@PathVariable String passportNumber) {
        log.info("Deleting passenger with passport number {}", passportNumber);
        passengerService.deletePassenger(passportNumber);
    }

    /*
        GET api/passengers/{name} - get passenger by passport number
     */
    @GetMapping(value = "/passengers/{passportNumber}")
    Passenger getPassengerByPassportNumber(@PathVariable String passportNumber) {
        log.info("Getting passenger with passport number {}", passportNumber);
        return passengerService.getPassengerByPassportNumber(passportNumber);
    }

    /*
        GET api/passengers - get all passengers
     */
    @GetMapping(value = "/")
    Iterable<Passenger> getAllPassengers() {
        log.info("Getting all passengers");
        return passengerService.getAllPassengers();
    }

    /*
        PUT api/passengers/{name}/update - update passenger
     */
    @PutMapping(value = "/update/{passportNumber}")
    Passenger updatePassenger(@PathVariable String passportNumber, @RequestBody @NotNull PassengerDto dto) {
        log.info("Updating passenger with passport number {}", passportNumber);
        return passengerService.updatePassenger(dto.getName(), dto.getSurname(), dto.getPassportNumber(), dto.getDateOfBirth(), dto.getPhoneNumber());
    }

    // add passenger to trip
    @PutMapping(value = "/addToTrip/{passportNumber}")
    Passenger addPassengerToTrip(@PathVariable String passportNumber, String companyName, String townFrom, String townTo, Integer seatNumber) {
        log.info("Adding passenger with passport number {} to {}'s company trip from {} to {} with seat number {}", passportNumber , companyName, townFrom, townTo, seatNumber);
        return passengerService.addPassengerToTrip(passportNumber, companyName, townFrom, townTo, seatNumber);
    }

}
