package com.hackload.billetter_api.dto.seat;

import java.math.BigDecimal;

public record ListSeatsResponseItem(long id, int row, int number, SeatStatus status, BigDecimal price) {}

