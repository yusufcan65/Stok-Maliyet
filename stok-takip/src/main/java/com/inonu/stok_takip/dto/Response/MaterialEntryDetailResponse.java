package com.inonu.stok_takip.dto.Response;

public record MaterialEntryDetailResponse(
        String productName,
        String measurementType,
        Double totalDevir,
        Double totalAlim,
        Double totalIhale,
        Double total22f,
        Double total19a,
        Double total21d,
        Double totalCikis,
        Double kalanMiktar
) {
}
