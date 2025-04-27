package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.DateRequest;
import com.inonu.stok_takip.dto.Request.MaterialEntryCreateRequest;
import com.inonu.stok_takip.dto.Request.MaterialEntryUpdateRequest;
import com.inonu.stok_takip.dto.Response.MaterialEntryResponse;
import com.inonu.stok_takip.entitiy.MaterialEntry;

import java.util.List;

public interface MaterialEntryService {

    List<MaterialEntryResponse> getAllMaterialEntryList();
    MaterialEntryResponse createMaterialEntry(MaterialEntryCreateRequest request);
    MaterialEntryResponse updateMaterialEntry(MaterialEntryUpdateRequest request);
    MaterialEntry getMaterialEntryById(Long id);
    MaterialEntryResponse deleteMaterialEntry(Long id);
    List<MaterialEntry> getMaterialEntryByProductId(Long productId);
    Double updateRemainingQuantity(Long materialEntryId, Double exitQuantity);
    List<MaterialEntryResponse> carryOverEntriesToNextYear(DateRequest request);

    // bundan sonrası stok çıkışı için yazılmış
    Double getTotalRemainingQuantity(Long productId);
}

