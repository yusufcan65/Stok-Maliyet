package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.CreatePurchasedUnitRequest;
import com.inonu.stok_takip.dto.Response.PurchasedUnitResponse;
import com.inonu.stok_takip.entitiy.PurchasedUnit;

import java.util.List;

public interface PurchasedUnitService {
    List<PurchasedUnitResponse> getAll();
    PurchasedUnitResponse create(CreatePurchasedUnitRequest request);
    PurchasedUnitResponse update(Long id, CreatePurchasedUnitRequest request);
    PurchasedUnit getById(Long id);
    PurchasedUnitResponse delete(Long id);
}
