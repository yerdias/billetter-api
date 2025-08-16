package com.hackload.billetter_api.service;

import com.hackload.billetter_api.dto.seat.ListSeatsResponseItem;
import com.hackload.billetter_api.dto.seat.SeatStatus;

import java.util.List;

public interface SeatService {
    List<ListSeatsResponseItem> listSeats(long eventId, Integer row, SeatStatus status, int page, int pageSize);
    boolean select(long bookingId, long seatId);
    boolean release(long seatId);
}

