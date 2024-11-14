package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.Integration.Payment.Paypal.DTO.OrderCaptureResponse;
import com.example.orientacionvocacionalapi.Integration.Payment.Paypal.DTO.OrderResponse;
import com.example.orientacionvocacionalapi.Integration.Payment.Paypal.Service.PayPalService;
import com.example.orientacionvocacionalapi.dto.PaymentCaptureResponse;
import com.example.orientacionvocacionalapi.dto.PaymentOrderResponse;
import com.example.orientacionvocacionalapi.dto.PurchaseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService{
    private final PayPalService payPalService;
    private final PurchaseService purchaseService;

    @Override
    public PaymentOrderResponse createPaymentOrder(Integer purchaseId, String returnUrl, String cancelUrl) {
        OrderResponse orderResponse = payPalService.createOrder(purchaseId, returnUrl, cancelUrl);
        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();
        return new PaymentOrderResponse(paypalUrl);
    }

    @Override
    public PaymentCaptureResponse capturePaymentOrder(String orderId) {
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();
            PurchaseDTO purchaseDTO = purchaseService.confirmPurchase(Integer.parseInt(purchaseIdStr));
            paypalCaptureResponse.setPurchaseId(purchaseDTO.getId());
        }
        return paypalCaptureResponse;
    }

}