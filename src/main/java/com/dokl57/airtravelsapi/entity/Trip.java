package com.dokl57.airtravelsapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "town_from", nullable = false)
    private String townFrom;

    @Column(name = "town_to", nullable = false)
    private String townTo;

    @Column(name = "time_in", nullable = false)
    private LocalDateTime timeIn;

    @Column(name = "time_out", nullable = false)
    private LocalDateTime timeOut;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
    Set<PassInTrip> passInTrips;

}
