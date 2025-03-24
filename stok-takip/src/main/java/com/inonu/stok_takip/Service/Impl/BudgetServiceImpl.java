package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.BudgetRepository;
import com.inonu.stok_takip.Service.BudgetService;
import com.inonu.stok_takip.dto.Request.CreateBudgetRequest;
import com.inonu.stok_takip.dto.Response.BudgetResponse;
import com.inonu.stok_takip.entitiy.Budget;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public List<BudgetResponse> getAllBudgets() {
        return budgetRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public BudgetResponse createBudget(CreateBudgetRequest request) {
        Budget budget = new Budget();
        budget.setBudgetName(request.budgetName());
        budget.setBudgetAmount(request.budgetAmount());
        budget.setRemainingBudgetAmount(request.remainingBudgetAmount());
        budget.setStartDate(request.startDate());
        budget.setEndDate(request.endDate());
        return mapToResponse(budgetRepository.save(budget));
    }

    @Override
    public BudgetResponse updateBudget(Long id, CreateBudgetRequest request) {
        Budget budget = getBudgetById(id);
        budget.setBudgetName(request.budgetName());
        budget.setBudgetAmount(request.budgetAmount());
        budget.setRemainingBudgetAmount(request.remainingBudgetAmount());
        budget.setStartDate(request.startDate());
        budget.setEndDate(request.endDate());
        return mapToResponse(budgetRepository.save(budget));
    }

    @Override
    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Budget not found with id: " + id));
    }

    @Override
    public BudgetResponse deleteBudget(Long id) {
        Budget budget = getBudgetById(id);
        budgetRepository.delete(budget);
        return mapToResponse(budget);
    }

    private BudgetResponse mapToResponse(Budget budget) {
        return new BudgetResponse(
            budget.getId(),
            budget.getBudgetName(),
            budget.getBudgetAmount(),
            budget.getRemainingBudgetAmount(),
            budget.getStartDate(),
            budget.getEndDate()
        );
    }
}
