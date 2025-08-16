package com.hackload.billetter_api.dto.seat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record SelectSeatRequest(
        @JsonProperty("booking_id") @NotNull Long bookingId,
        @JsonProperty("seat_id") @NotNull Long seatId
) {}

