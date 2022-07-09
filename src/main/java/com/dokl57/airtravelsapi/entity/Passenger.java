package com.dokl57.airtravelsapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

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
    @ToString.Exclude
    @JsonIgnoreProperties({"passenger"})
    private Set<PassInTrip> passInTrips;


    public List<Trip> getTrips() {
        return passInTrips.stream().map(PassInTrip::getTrip).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Passenger passenger = (Passenger) o;
        return id != null && Objects.equals(id, passenger.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
