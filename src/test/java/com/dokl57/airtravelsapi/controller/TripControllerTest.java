package com.dokl57.airtravelsapi.controller;

import com.dokl57.airtravelsapi.dto.TripDto;
import com.dokl57.airtravelsapi.entity.Trip;
import com.dokl57.airtravelsapi.service.TripService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TripControllerTest {

    @Mock
    private TripService tripService;

    @InjectMocks
    private TripController tripController;

    @Test
    public void testGetAllTrips() {
        // prepare
        when(tripService.getAllTrips()).thenReturn(ImmutableList.of());

        // testing
        Iterable<Trip> listOfTrips = tripController.getAllTrips();

        // validate
        verify(tripService).getAllTrips();
    }

    @Test
    public void testGetTripById(){
        // prepare
        UUID id = UUID.randomUUID();
        when(tripService.getTripById(id)).thenReturn(new Trip());

        // testing
        Trip trip = tripController.getTripById(id);

        // validate
        verify(tripService).getTripById(id);
    }

    @Test
    public void testCreateTrip(){
        // prepare
        // companyId townFrom townTo timeIn timeOut
        UUID companyId = UUID.randomUUID();
        String townFrom = "townFrom";
        String townTo = "townTo";
        LocalDateTime timeIn = LocalDateTime.now();
        LocalDateTime timeOut = LocalDateTime.now();
        TripDto tripDto = new TripDto(companyId, townFrom, townTo, timeIn, timeOut);
        Trip trip = new Trip();
        when(tripService.createTrip(companyId, townFrom, townTo, timeIn, timeOut)).thenReturn(trip);

        // testing
        Trip createdTrip = tripController.createTrip(tripDto);

        // validate
        verify(tripService).createTrip(companyId, townFrom, townTo, timeIn, timeOut);
    }

    @Test
    public void testUpdateTrip(){
        // prepare
        // companyId townFrom townTo timeIn timeOut
        UUID id = UUID.randomUUID();
        UUID companyId = UUID.randomUUID();
        String townFrom = "townFrom";
        String townTo = "townTo";
        LocalDateTime timeIn = LocalDateTime.now();
        LocalDateTime timeOut = LocalDateTime.now();
        TripDto tripDto = new TripDto(companyId, townFrom, townTo, timeIn, timeOut);
        Trip trip = new Trip();
        when(tripService.updateTrip(id, townFrom, townTo, timeIn, timeOut)).thenReturn(trip);

        // testing
        Trip updatedTrip = tripController.updateTrip(id, tripDto);

        // validate
        verify(tripService).updateTrip(id, townFrom, townTo, timeIn, timeOut);
    }
}
