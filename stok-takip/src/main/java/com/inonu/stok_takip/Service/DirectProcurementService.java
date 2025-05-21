package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.DirectProcurementCreateRequest;
import com.inonu.stok_takip.dto.Response.DirectProcurementResponse;
import com.inonu.stok_takip.entitiy.DirectProcurement;

import java.util.List;

public interface DirectProcurementService {

    DirectProcurement createDirectProcurement(DirectProcurementCreateRequest request);
    List<DirectProcurementResponse> getAllDirectProcurements();
    DirectProcurement getDirectProcurementById(Long id);
    DirectProcurementResponse updateRemainingQuantity(Long directProcurementId, Double quantity);
    List<DirectProcurementResponse> getDirectProcurementsByProductAndCompany();

}
