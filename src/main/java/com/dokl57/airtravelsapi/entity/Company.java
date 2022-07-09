package com.dokl57.airtravelsapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @ToString.Exclude
    @JsonIgnoreProperties({"company"})
    private Set<Trip> trips = new HashSet<>();



    public Trip addTripToCompany(Trip trip) {
        trips.add(trip);
        trip.setCompany(this);
        return trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;
        return id != null && Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
