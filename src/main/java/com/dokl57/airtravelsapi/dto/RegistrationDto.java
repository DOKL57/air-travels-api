package com.dokl57.airtravelsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private UUID passengerId;
    private UUID tripId;
    @Min(1)
    @Max(200)
    private int seatNumber;
}
