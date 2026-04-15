package com.app.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BookingResponse {
    private UUID bookingId;
    private String status;
}
