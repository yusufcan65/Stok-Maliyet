package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.PurchasedUnitRepository;
import com.inonu.stok_takip.Service.PurchasedUnitService;
import com.inonu.stok_takip.dto.Request.CreatePurchasedUnitRequest;
import com.inonu.stok_takip.dto.Response.PurchasedUnitResponse;
import com.inonu.stok_takip.entitiy.PurchasedUnit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchasedUnitServiceImpl implements PurchasedUnitService {

    private final PurchasedUnitRepository repository;

    public PurchasedUnitServiceImpl(PurchasedUnitRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PurchasedUnitResponse> getAll() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public PurchasedUnitResponse create(CreatePurchasedUnitRequest request) {
        PurchasedUnit unit = new PurchasedUnit();
        unit.setName(request.name());
        return mapToResponse(repository.save(unit));
    }

    @Override
    public PurchasedUnitResponse update(Long id, CreatePurchasedUnitRequest request) {
        PurchasedUnit unit = getById(id);
        unit.setName(request.name());
        return mapToResponse(repository.save(unit));
    }

    @Override
    public PurchasedUnit getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("PurchasedUnit not found: " + id));
    }

    @Override
    public PurchasedUnitResponse delete(Long id) {
        PurchasedUnit unit = getById(id);
        repository.delete(unit);
        return mapToResponse(unit);
    }

    private PurchasedUnitResponse mapToResponse(PurchasedUnit unit) {
        return new PurchasedUnitResponse(unit.getId(), unit.getName());
    }
}
