package com.app.booking.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatLockService {

    private final StringRedisTemplate redisTemplate;

    public SeatLockService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void lockSeats(String showId, List<String> seats) {
        for (String seat : seats) {
            String key = "seat:lock:" + showId + ":" + seat;
            Boolean success = redisTemplate.opsForValue()
                    .setIfAbsent(key, "locked");

            if (Boolean.FALSE.equals(success)) {
                throw new RuntimeException("Seat already locked");
            }
        }
    }
}
