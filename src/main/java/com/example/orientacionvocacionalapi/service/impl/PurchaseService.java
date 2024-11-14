package com.example.orientacionvocacionalapi.service.impl;

import com.example.orientacionvocacionalapi.dto.PurchaseCreateDTO;
import com.example.orientacionvocacionalapi.dto.PurchaseDTO;
import com.example.orientacionvocacionalapi.dto.PurchaseReportDTO;

import java.util.List;

public interface PurchaseService {
    PurchaseDTO createPurchase(PurchaseCreateDTO purchase);
    List<PurchaseDTO> getPurchaseHistoryByUserId(Integer userId);
    List<PurchaseReportDTO> getPurchaseReportByDate();


    List<PurchaseDTO> getAllPurchases();
    PurchaseDTO confirmPurchase(Integer purchaseId);
    PurchaseDTO getPurchaseById(Integer purchaseId);
}