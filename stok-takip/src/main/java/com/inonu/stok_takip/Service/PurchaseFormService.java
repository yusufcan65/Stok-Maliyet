package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.PurchaseFormCreateRequest;
import com.inonu.stok_takip.dto.Response.PurchaseFormResponse;
import com.inonu.stok_takip.entitiy.PurchaseForm;

import java.util.List;

public interface PurchaseFormService {

    List<PurchaseFormResponse> getAllPurchaseForm();
    PurchaseFormResponse createPurchaseForm(PurchaseFormCreateRequest request);
    PurchaseFormResponse updatePurchaseForm(PurchaseFormCreateRequest request);
    PurchaseForm getPurchaseFormById(Long id);
    PurchaseFormResponse deletePurchaseForm(Long id);
    PurchaseForm getPurchaseFormNameById(Long id);
}
