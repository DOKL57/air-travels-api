package com.dokl57.airtravelsapi.controller;

import com.dokl57.airtravelsapi.dto.RegistrationDto;
import com.dokl57.airtravelsapi.service.RegistrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    @Mock
    private RegistrationService registrationService;

    @InjectMocks
    private RegistrationController registrationController;

    @Test
    public void testRegisterPassenger(){
        // prepare
        UUID passengerId = UUID.randomUUID();
        UUID tripId = UUID.randomUUID();
        Integer seatNumber = 5;
        RegistrationDto registrationDto = new RegistrationDto(passengerId, tripId, seatNumber);

        // testing
        registrationController.registerPassenger(registrationDto);

        // validate
        verify(registrationService).registerPassengerToTrip(passengerId, tripId, seatNumber);

    }

}
