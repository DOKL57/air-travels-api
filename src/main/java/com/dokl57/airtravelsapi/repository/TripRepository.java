package com.dokl57.airtravelsapi.repository;

import com.dokl57.airtravelsapi.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {

}
