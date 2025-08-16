package com.hackload.billetter_api.service;

import com.hackload.billetter_api.dto.payment.PaymentNotificationPayload;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void handlePaymentSuccess(Long orderId) {
        // 1. Load booking by orderId
        // 2. Mark as PAID + CONFIRMED
        // 3. Save and maybe send email/notification
    }

    public void handlePaymentFailure(Long orderId) {
        // 1. Load booking by orderId
        // 2. Cancel booking + release seats
        // 3. Save and maybe notify user
    }

    public void handlePaymentNotification(PaymentNotificationPayload payload) {
        // 1. Validate signature if provided
        // 2. Update transaction status in DB
        // 3. If success/fail -> trigger corresponding logic
    }
}
