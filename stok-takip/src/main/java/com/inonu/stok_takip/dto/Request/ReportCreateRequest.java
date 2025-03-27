package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;

public record ReportCreateRequest(
    String reportType,
    LocalDate reportCreateDate,
    int ticketQuantity,
    Double totalTicketPrice,
    int totalPersonQuantity,
    Double totalMaterialPrice,
    Double totalCleanPrice,
    Double averagePersonCost,
    Double averageTicketCot
) {
    
}
