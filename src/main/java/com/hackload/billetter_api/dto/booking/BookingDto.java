package com.hackload.billetter_api.dto.booking;

import lombok.Data;

import java.util.List;

@Data
public class BookingDto {
    private long id;
    private long eventId;
    private List<Long> seats;
}
