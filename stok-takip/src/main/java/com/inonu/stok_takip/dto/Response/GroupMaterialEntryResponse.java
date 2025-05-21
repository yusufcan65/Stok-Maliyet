package com.inonu.stok_takip.dto.Response;

import java.util.List;

public record GroupMaterialEntryResponse(
        String purchaseFormName,
        double totalAmount,
        List<MaterialEntryProductResponse> productResponses
) {
}
