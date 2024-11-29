package com.example.orientacionvocacionalapi.dto;

import com.example.orientacionvocacionalapi.model.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseDTO {
    private Integer id;
    private Float total;
    private LocalDateTime createAt;
    private PaymentStatus paymentStatus;
    private Integer userId;
    private List<PurchaseItemDTO> items;
}
