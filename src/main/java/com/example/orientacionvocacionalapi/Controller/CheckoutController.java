package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.dto.PaymentCaptureResponse;
import com.example.orientacionvocacionalapi.dto.PaymentOrderResponse;
import com.example.orientacionvocacionalapi.service.impl.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/checkout")
@RestController
public class CheckoutController {
    private final CheckoutService checkoutService;


    @PostMapping("/create")
    public ResponseEntity<PaymentOrderResponse> createPaymentOrder(
            @RequestParam Integer purchaseId,
            @RequestParam String returnUrl,
            @RequestParam String cancelUrl,
            @RequestParam(required = false, defaultValue = "paypal") String paymentProvider
    ){
        PaymentOrderResponse response = checkoutService.createPaymentOrder(purchaseId, returnUrl, cancelUrl);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/capture")
    public ResponseEntity<PaymentCaptureResponse> capturePaymentOrder(
            @RequestParam String orderId,
            @RequestParam(required = false, defaultValue = "paypal") String paymentProvider
    ){
        PaymentCaptureResponse response = checkoutService.capturePaymentOrder(orderId);
        if (response.isCompleted()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}