package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.dto.PaymentCaptureResponse;
import com.example.orientacionvocacionalapi.dto.PaymentOrderResponse;

public interface CheckoutService {

    PaymentOrderResponse createPaymentOrder(Integer purchaseId, String returnUrl, String cancelUrl);

    PaymentCaptureResponse capturePaymentOrder(String orderId);
}