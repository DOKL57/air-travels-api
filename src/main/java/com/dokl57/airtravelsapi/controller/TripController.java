package com.dokl57.airtravelsapi.controller;

import com.dokl57.airtravelsapi.dto.TripDto;
import com.dokl57.airtravelsapi.entity.Passenger;
import com.dokl57.airtravelsapi.entity.Trip;
import com.dokl57.airtravelsapi.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@Slf4j
@Validated
@RequestMapping("/api/trip")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }
        /*
        POST /api/trip/create - create new passenger
     */

    @PostMapping(value = "/create")
    Trip createTrip(@RequestBody @NotNull TripDto dto) {
        log.info("Creating new trip for company with id {}", dto.getCompanyId());
        return tripService.createTrip(dto.getCompanyId(), dto.getTownFrom(), dto.getTownTo(), dto.getTimeIn(), dto.getTimeOut());
    }

    /*
        PUT api/trip/{id}/update - update trip by id
     */
    @PutMapping(value = "/{id}/update")
    Trip updateTrip(@PathVariable UUID id, @RequestBody @NotNull TripDto dto) {
        log.info("Updating trip with id {}", id);
        return tripService.updateTrip(id, dto.getTownFrom(), dto.getTownTo(), dto.getTimeIn(), dto.getTimeOut());
    }

    /*
        GET /api/trip/{id} - get trip by id
     */
    @GetMapping(value = "/{id}")
    Trip getTripById(@PathVariable UUID id) {
        log.info("Getting trip with id {}", id);
        return tripService.getTripById(id);
    }

    /*
        GET /api/trip/ - get all trips
     */
    @GetMapping(value = "/")
    Iterable<Trip> getAllTrips() {
        log.info("Getting all trips");
        return tripService.getAllTrips();
    }
}
