package com.dokl57.airtravelsapi.repository;

import com.dokl57.airtravelsapi.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
