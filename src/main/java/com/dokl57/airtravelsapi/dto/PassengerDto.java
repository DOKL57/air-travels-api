package com.dokl57.airtravelsapi.dto;

import com.dokl57.airtravelsapi.entity.PassInTrip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {
    private String name;
    private String surname;
    @Size(min = 10, max = 15, message = "Number should have at least 10 or less than 15 digits")
    private String phoneNumber;
    private String passportNumber;
    private LocalDate dateOfBirth;
}
