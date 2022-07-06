package com.dokl57.airtravelsapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "passenger")
    private Set<PassInTrip> passInTrips;


    public Passenger(Long randomUUID, String name, String surname, String passportNumber, LocalDate dateOfBirth, String phoneNumber) {
        this.id = Long.valueOf(randomUUID.toString());
        this.name = name;
        this.surname = surname;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }


}
