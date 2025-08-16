package com.hackload.billetter_api.controller;

import com.hackload.billetter_api.dto.booking.BookingDto;
import com.hackload.billetter_api.dto.booking.CancelBookingRequest;
import com.hackload.billetter_api.dto.booking.CreateBookingRequest;
import com.hackload.billetter_api.dto.booking.CreateBookingResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @PostMapping
    public ResponseEntity<CreateBookingResponse> createBooking(@RequestBody CreateBookingRequest request) {
        CreateBookingResponse response = new CreateBookingResponse();
        response.setId(UUID.randomUUID());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> getBookings() {
        BookingDto booking = new BookingDto();

        return ResponseEntity.ok(List.of(booking));
    }


    @PatchMapping("/cancel")
    public ResponseEntity<String> cancelBooking(@RequestBody CancelBookingRequest request) {
        request.setBookingId(UUID.randomUUID());
        return ResponseEntity.ok("Бронь успешно отменена");
    }
}

