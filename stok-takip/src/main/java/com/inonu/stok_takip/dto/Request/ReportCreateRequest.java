package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;

public record ReportCreateRequest(
    //String reportType, start ve end tarihine gore belirlenebilir 
    LocalDate startDate,
    LocalDate endDate,
    LocalDate reportCreateDate
) {
    
}
