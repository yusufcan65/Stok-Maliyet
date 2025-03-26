package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.PurchaseTypeRepository;
import com.inonu.stok_takip.Service.PurchaseTypeService;
import com.inonu.stok_takip.dto.Request.PurchaseTypeCreateRequest;
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
    public PurchaseTypeResponse create(PurchaseTypeCreateRequest request) {
        PurchaseType type = new PurchaseType();
        type.setName(request.name());
        return mapToResponse(repository.save(type));
    }

    @Override
    public PurchaseTypeResponse update(Long id, PurchaseTypeCreateRequest request) {
        PurchaseType type = getPurchaseTypeById(id);
        type.setName(request.name());
        return mapToResponse(repository.save(type));
    }

    @Override
    public PurchaseType getPurchaseTypeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("PurchaseType not found: " + id));
    }

    @Override
    public PurchaseTypeResponse delete(Long id) {
        PurchaseType type = getPurchaseTypeById(id);
        repository.delete(type);
        return mapToResponse(type);
    }

    private PurchaseTypeResponse mapToResponse(PurchaseType type) {
        return new PurchaseTypeResponse(type.getId(), type.getName());
    }
}
