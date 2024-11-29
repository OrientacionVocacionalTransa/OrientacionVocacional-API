package com.example.orientacionvocacionalapi.dto;

import lombok.Data;

@Data
public class PaymentCaptureResponse {
    private boolean completed;
    private Integer purchaseId;
    private String newToken;
}