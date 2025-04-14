package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.MaterialDemandCreateRequest;
import com.inonu.stok_takip.dto.Response.MaterialDemandResponse;
import com.inonu.stok_takip.entitiy.MaterialDemand;

import java.util.List;

public interface MaterialDemandService {
    MaterialDemandResponse createMaterialDemand(MaterialDemandCreateRequest request);
    MaterialDemand getMaterialDemandById(Long id);
    List<MaterialDemandResponse> getAllMaterialDemand();
    MaterialDemandResponse updateMaterialDemand(MaterialDemandCreateRequest request);
    MaterialDemandResponse deleteMaterialDemand(Long id);
}
