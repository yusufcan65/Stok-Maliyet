package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.PurchaseTypeRepository;
import com.inonu.stok_takip.Service.PurchaseTypeService;
import com.inonu.stok_takip.dto.Request.CreatePurchaseTypeRequest;
import com.inonu.stok_takip.dto.Response.PurchaseTypeResponse;
import com.inonu.stok_takip.entitiy.PurchaseType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseTypeServiceImpl implements PurchaseTypeService {

    private final PurchaseTypeRepository repository;

    public PurchaseTypeServiceImpl(PurchaseTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PurchaseTypeResponse> getAll() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public PurchaseTypeResponse create(CreatePurchaseTypeRequest request) {
        PurchaseType type = new PurchaseType();
        type.setName(request.name());
        return mapToResponse(repository.save(type));
    }

    @Override
    public PurchaseTypeResponse update(Long id, CreatePurchaseTypeRequest request) {
        PurchaseType type = getById(id);
        type.setName(request.name());
        return mapToResponse(repository.save(type));
    }

    @Override
    public PurchaseType getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("PurchaseType not found: " + id));
    }

    @Override
    public PurchaseTypeResponse delete(Long id) {
        PurchaseType type = getById(id);
        repository.delete(type);
        return mapToResponse(type);
    }

    private PurchaseTypeResponse mapToResponse(PurchaseType type) {
        return new PurchaseTypeResponse(type.getId(), type.getName());
    }
}
