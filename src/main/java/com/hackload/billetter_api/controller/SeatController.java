package com.hackload.billetter_api.controller;

import com.hackload.billetter_api.dto.seat.ListSeatsResponseItem;
import com.hackload.billetter_api.dto.seat.ReleaseSeatRequest;
import com.hackload.billetter_api.dto.seat.SeatStatus;
import com.hackload.billetter_api.dto.seat.SelectSeatRequest;
import com.hackload.billetter_api.service.SeatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/seats")
    public List<ListSeatsResponseItem> listSeats(
            @RequestParam("event_id") @NotNull Long eventId,
            @RequestParam(value = "row", required = false) @Min(1) Integer row,
            @RequestParam(value = "status", required = false) SeatStatus status,
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(20) int pageSize
    ) {
        return seatService.listSeats(eventId, row, status, page, pageSize);
    }

    @PatchMapping("/seats/select")
    public ResponseEntity<Void> select(@RequestBody @Valid SelectSeatRequest req) {
        boolean ok = seatService.select(req.bookingId(), req.seatId());
        return ok ? ResponseEntity.ok().build() : ResponseEntity.status(419).build();
    }

    @PatchMapping("/seats/release")
    public ResponseEntity<Void> release(@RequestBody @Valid ReleaseSeatRequest req) {
        boolean ok = seatService.release(req.seatId());
        return ok ? ResponseEntity.ok().build() : ResponseEntity.status(419).build();
    }
}

