package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.MaterialDemandApprovedRequest;
import com.inonu.stok_takip.dto.Request.MaterialDemandCreateRequest;
import com.inonu.stok_takip.dto.Request.MaterialDemandUpdateRequest;
import com.inonu.stok_takip.dto.Response.MaterialDemandResponse;
import com.inonu.stok_takip.entitiy.MaterialDemand;

import java.util.List;

public interface MaterialDemandService {
    MaterialDemandResponse createMaterialDemand(MaterialDemandCreateRequest request);
    MaterialDemand getMaterialDemandById(Long id);
    List<MaterialDemandResponse> getAllMaterialDemand();
    MaterialDemandResponse updateMaterialDemand(MaterialDemandUpdateRequest request);
    MaterialDemandResponse deleteMaterialDemand(Long id);
    MaterialDemandResponse rejectMaterialDemand(Long id, String rejectionReason);
    MaterialDemandResponse approveAndProcessMaterialDemand(MaterialDemandApprovedRequest request);

    // bundan sonrası değişikliklerden sonra ihale materialEntryden ayrıldıktan sonra eklendi

    void checkStockAvailabilityByProductInTender(Long productId, Double requestedQuantity);
}
