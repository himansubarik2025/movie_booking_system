package com.app.booking.controller;

import com.app.booking.service.BookingService;
import com.app.booking.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponse create(@RequestBody BookingRequest request,
                                  @RequestHeader("X-Idempotency-Key") String key) {
        return bookingService.createBooking(request, key);
    }
}
