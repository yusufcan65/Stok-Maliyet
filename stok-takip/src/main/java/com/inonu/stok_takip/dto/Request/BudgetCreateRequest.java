package com.inonu.stok_takip.dto.Request;

import java.time.LocalDate;

public record BudgetCreateRequest(
    String budgetName,
    Double budgetAmount,
    Double remainingBudgetAmount,
    LocalDate startDate,
    LocalDate endDate
) {}
