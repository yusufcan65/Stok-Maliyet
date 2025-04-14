package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.MaterialExitCreateRequest;
import com.inonu.stok_takip.dto.Response.MaterialExitResponse;
import com.inonu.stok_takip.entitiy.MaterialExit;

import java.util.List;

public interface MaterialExitService {

    List<MaterialExitResponse> getAllMaterialExits();
    List<MaterialExitResponse> createMaterialExit(MaterialExitCreateRequest request);
    MaterialExitResponse updateMaterialExit(MaterialExitCreateRequest request);
    MaterialExitResponse deleteMaterialExit(Long id);
    MaterialExit getMaterialExitById(Long id);


}
