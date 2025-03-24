package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.CreateBudgetRequest;
import com.inonu.stok_takip.dto.Response.BudgetResponse;
import com.inonu.stok_takip.entitiy.Budget;

import java.util.List;

public interface BudgetService {
    List<BudgetResponse> getAllBudgets();
    BudgetResponse createBudget(CreateBudgetRequest request);
    BudgetResponse updateBudget(Long id, CreateBudgetRequest request);
    Budget getBudgetById(Long id);
    BudgetResponse deleteBudget(Long id);
}
