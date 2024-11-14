package com.example.orientacionvocacionalapi.repository;

import com.example.orientacionvocacionalapi.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByUserId(Integer userId);

    @Query(value = "SELECT * FROM fn_list_purchase_report()", nativeQuery = true )
    List<Object[]> getPurchaseReportByDate();
}
