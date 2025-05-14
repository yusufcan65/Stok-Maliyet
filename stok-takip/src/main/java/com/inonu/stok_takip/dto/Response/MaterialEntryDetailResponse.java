package com.inonu.stok_takip.dto.Response;

public record MaterialEntryDetailResponse(
        ProductDetailResponse productResponse,
        Double totalDevir,
        Double totalAlim,
        Double totalIhale,
        Double total22d,
        Double total19f,
        Double total21a,
        Double totalCikis,
        Double kalanMiktar
) {
}
