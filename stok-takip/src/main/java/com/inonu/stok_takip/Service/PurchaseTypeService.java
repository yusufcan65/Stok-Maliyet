package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.CreatePurchaseTypeRequest;
import com.inonu.stok_takip.dto.Response.PurchaseTypeResponse;
import com.inonu.stok_takip.entitiy.PurchaseType;

import java.util.List;

public interface PurchaseTypeService {
    List<PurchaseTypeResponse> getAll();
    PurchaseTypeResponse create(CreatePurchaseTypeRequest request);
    PurchaseTypeResponse update(Long id, CreatePurchaseTypeRequest request);
    PurchaseType getById(Long id);
    PurchaseTypeResponse delete(Long id);
}
