package com.dokl57.airtravelsapi.controller;

import com.dokl57.airtravelsapi.dto.RegistrationDto;
import com.dokl57.airtravelsapi.entity.PassInTrip;
import com.dokl57.airtravelsapi.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@Validated
@RequestMapping("/api/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /*
        POST /api/registration/new - register a new passenger to trip
     */
    @PostMapping(value = "/new")
    PassInTrip registerPassenger(@RequestBody @NotNull @Valid RegistrationDto dto) {
        log.info("Registering new passenger with id {} to trip with id {} and seat number {}", dto.getPassengerId(), dto.getTripId(), dto.getSeatNumber());
        return registrationService.registerPassengerToTrip(dto.getPassengerId(), dto.getTripId(), dto.getSeatNumber());
    }
}
