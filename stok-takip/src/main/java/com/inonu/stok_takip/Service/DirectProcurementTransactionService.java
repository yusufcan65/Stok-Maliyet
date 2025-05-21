package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.DirectProcurementCreateRequest;
import com.inonu.stok_takip.dto.Response.DirectProcurementResponse;

public interface DirectProcurementTransactionService {
    DirectProcurementResponse createDirectAndMaterialEntry(DirectProcurementCreateRequest request);
}
