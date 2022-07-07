package com.dokl57.airtravelsapi.repository;

import com.dokl57.airtravelsapi.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {


    Optional<Passenger> findPassengerByName(String name);
    Optional<Passenger> findPassengerByPassportNumber(String passportNumber);
}
