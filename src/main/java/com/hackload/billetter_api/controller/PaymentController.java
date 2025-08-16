package com.hackload.billetter_api.controller;

import com.hackload.billetter_api.dto.payment.PaymentNotificationPayload;
import com.hackload.billetter_api.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Платеж проведен успешно
     * Бронь становится подтвержденной и оплаченной
     */
    @GetMapping("/success")
    public ResponseEntity<Void> notifyPaymentCompleted(@RequestParam Long orderId) {
        paymentService.handlePaymentSuccess(orderId);
        return ResponseEntity.ok().build();
    }

    /**
     * Платеж проведен неуспешно
     * Бронь отменяется и места освобождаются
     */
    @GetMapping("/fail")
    public ResponseEntity<Void> notifyPaymentFailed(@RequestParam Long orderId) {
        paymentService.handlePaymentFailure(orderId);
        return ResponseEntity.ok().build();
    }

    /**
     * Принимать уведомления от платежного шлюза
     */
    @PostMapping("/notifications")
    public ResponseEntity<Void> onPaymentUpdates(@RequestBody PaymentNotificationPayload payload) {
        paymentService.handlePaymentNotification(payload);
        return ResponseEntity.ok().build();
    }
}

