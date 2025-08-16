package com.hackload.billetter_api.dto.booking;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BookingDto {
    private UUID id;
    private UUID eventId;
    private List<UUID> seats; // только список id мест
}
