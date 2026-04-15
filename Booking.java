package com.app.booking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Booking {

    @Id
    private UUID bookingId;

    private UUID showId;

    private String status;

    @Column(unique = true)
    private String idempotencyKey;
}
