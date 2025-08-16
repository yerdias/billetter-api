package com.hackload.billetter_api.service.impl;

import com.hackload.billetter_api.dto.seat.ListSeatsResponseItem;
import com.hackload.billetter_api.dto.seat.SeatStatus;
import com.hackload.billetter_api.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StubSeatService implements SeatService {
    @Override
    public List<ListSeatsResponseItem> listSeats(long eventId, Integer row, SeatStatus status, int page, int pageSize) {
        // Temporary stub implementation; returns empty list
        return List.of();
    }

    @Override
    public boolean select(long bookingId, long seatId) {
        // Temporary stub implementation
        return true;
    }

    @Override
    public boolean release(long seatId) {
        // Temporary stub implementation
        return true;
    }
}

