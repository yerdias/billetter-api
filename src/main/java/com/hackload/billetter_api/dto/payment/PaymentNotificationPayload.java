package com.hackload.billetter_api.dto.payment;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class PaymentNotificationPayload {

    private String paymentId;

    private String status;

    private String teamSlug;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime timestamp;

    // Flexible "data" object — store as a Map
    private Map<String, Object> data = new HashMap<>();

    @JsonAnySetter
    public void addData(String key, Object value) {
        this.data.put(key, value);
    }
}
