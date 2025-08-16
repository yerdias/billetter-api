package com.hackload.billetter_api.dto.booking;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateBookingRequest {
    private long eventId;
}
