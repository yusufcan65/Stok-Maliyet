package com.inonu.stok_takip.Service.Impl;

import com.inonu.stok_takip.Repositoriy.PurchaseFormRepository;
import com.inonu.stok_takip.Service.PurchaseFormService;
import com.inonu.stok_takip.dto.Request.PurchaseFormCreateRequest;
import com.inonu.stok_takip.dto.Response.PurchaseFormResponse;
import com.inonu.stok_takip.entitiy.PurchaseForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseFormServiceImpl implements PurchaseFormService {

    private final PurchaseFormRepository purchaseFormRepository;

    public PurchaseFormServiceImpl(PurchaseFormRepository purchaseFormRepository) {
        this.purchaseFormRepository = purchaseFormRepository;
    }

    @Override
    public List<PurchaseFormResponse> getAllPurchaseForm() {
        List<PurchaseForm> purchaseFormList = purchaseFormRepository.findAll();
        return mapToResponseList(purchaseFormList);
    }

    @Override
    public PurchaseFormResponse createPurchaseForm(PurchaseFormCreateRequest request) {

        PurchaseForm purchaseForm = mapToEntity(request);
        PurchaseForm toSave = purchaseFormRepository.save(purchaseForm);
        return mapToResponse(toSave);

    }

    @Override
    public PurchaseFormResponse updatePurchaseForm(PurchaseFormCreateRequest request) {
        return null;
    }

    @Override
    public PurchaseForm getPurchaseFormById(Long id) {
        return purchaseFormRepository.findById(id).orElseThrow(()-> new RuntimeException("Purchase Form Not Found"));
    }

    @Override
    public PurchaseFormResponse deletePurchaseForm(Long id) {
        PurchaseForm purchaseForm = getPurchaseFormById(id);
        purchaseFormRepository.delete(purchaseForm);
        return mapToResponse(purchaseForm);
    }

    private PurchaseForm mapToEntity(PurchaseFormCreateRequest request) {
        PurchaseForm purchaseForm = new PurchaseForm();
        purchaseForm.setName(request.name());
        return purchaseForm;
    }

    private PurchaseFormResponse mapToResponse(PurchaseForm purchaseForm) {
        PurchaseFormResponse response = new PurchaseFormResponse(
                purchaseForm.getId(),
                purchaseForm.getName()
        );
        return response;
    }

    private List<PurchaseFormResponse> mapToResponseList(List<PurchaseForm> purchaseFormList) {
        List<PurchaseFormResponse> responseList = purchaseFormList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return responseList;
    }
}
