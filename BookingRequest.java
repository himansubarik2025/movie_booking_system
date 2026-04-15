package com.app.booking.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BookingRequest {
    private UUID showId;
    private List<String> seatIds;
}
