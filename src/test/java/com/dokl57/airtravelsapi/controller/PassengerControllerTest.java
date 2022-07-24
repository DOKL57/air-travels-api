package com.dokl57.airtravelsapi.controller;
import com.dokl57.airtravelsapi.dto.PassengerDto;
import com.dokl57.airtravelsapi.entity.Passenger;
import com.dokl57.airtravelsapi.service.PassengerService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PassengerControllerTest {

    @Mock
    private PassengerService passengerService;

    @InjectMocks
    private PassengerController passengerController;

    @Test
    public void testGetAllPassengers() {
        // prepare
        when(passengerService.getAllPassengers()).thenReturn(ImmutableList.of());

        // testing
        Iterable<Passenger> listOfPassengers = passengerController.getAllPassengers();

        // validate
        verify(passengerService).getAllPassengers();

    }

    @Test
    public void testGetPassengerByPassportNumber() {
        // prepare
        String passportNumber = "passportNumber";
        when(passengerService.getPassengerByPassportNumber(passportNumber)).thenReturn(new Passenger());

        // testing
        Passenger passenger = passengerController.getPassengerByPassportNumber(passportNumber);

        // validate
        verify(passengerService).getPassengerByPassportNumber(passportNumber);

    }

    @Test
    public void testCreatePassenger() {
        // prepare
        PassengerDto passengerDto = new PassengerDto("name", "surname", "phoneNumber", "passportNumber", LocalDate.now());
        Passenger passenger = new Passenger(UUID.randomUUID(), "name", "surname", "passportNumber", LocalDate.now(), "phoneNumber", new HashSet<>());
        when(passengerService.createPassenger(passengerDto.getName(), passengerDto.getSurname(), passengerDto
                .getPhoneNumber(), passengerDto.getDateOfBirth(), passengerDto.getPassportNumber())).thenReturn(passenger);
        // testing
        Passenger createdPassenger = passengerController.createPassenger(passengerDto);

        // validate
        verify(passengerService).createPassenger(passengerDto.getName(), passengerDto.getSurname(), passengerDto
                .getPassportNumber(), passengerDto.getDateOfBirth(), passengerDto.getPhoneNumber());
    }

    @Test
    public void testDeletePassenger() {
        // prepare
        String passportNumber = "passportNumber";
        // testing
        passengerController.deletePassenger(passportNumber);
        // validate
        verify(passengerService).deletePassenger(passportNumber);
    }

    @Test
    public void testUpdatePassenger() {
        // prepare
        PassengerDto passengerDto = new PassengerDto("name", "surname", "phoneNumber", "passportNumber", LocalDate.now());
        Passenger passenger = new Passenger(UUID.randomUUID(), "name", "surname", "passportNumber", LocalDate.now(), "phoneNumber", new HashSet<>());
        when(passengerService.updatePassenger(passengerDto.getName(), passengerDto.getSurname(), passengerDto
                .getPassportNumber(), passengerDto.getDateOfBirth(), passengerDto.getPhoneNumber())).thenReturn(passenger);
        // testing
        Passenger updatedPassenger = passengerController.updatePassenger(passengerDto.getPassportNumber(), passengerDto);
        // validate
        verify(passengerService).updatePassenger(passengerDto.getName(), passengerDto.getSurname(), passengerDto
                .getPassportNumber(), passengerDto.getDateOfBirth(), passengerDto.getPhoneNumber());
    }

}
