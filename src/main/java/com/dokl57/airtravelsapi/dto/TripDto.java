package com.dokl57.airtravelsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
    private UUID companyId;
    private String townFrom;
    private String townTo;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
}
