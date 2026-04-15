package com.app.booking.service;

import com.app.booking.entity.*;
import com.app.booking.repository.*;
import com.app.booking.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SeatLockService seatLockService;

    public BookingResponse createBooking(BookingRequest req, String key) {

        bookingRepository.findByIdempotencyKey(key)
                .ifPresent(b -> { throw new RuntimeException("Duplicate booking"); });

        seatLockService.lockSeats(req.getShowId(), req.getSeatIds());

        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID());
        booking.setShowId(req.getShowId());
        booking.setStatus("PENDING");
        booking.setIdempotencyKey(key);

        bookingRepository.save(booking);

        return new BookingResponse(booking.getBookingId(), "PENDING");
    }
}
