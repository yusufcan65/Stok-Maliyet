package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.PurchaseFormService;
import com.inonu.stok_takip.dto.Request.PurchaseFormCreateRequest;
import com.inonu.stok_takip.dto.Response.PurchaseFormResponse;
import com.inonu.stok_takip.dto.Response.RestResponse;
import com.inonu.stok_takip.entitiy.PurchaseForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/purchaseForm")
public class PurchaseFormController {

    private final PurchaseFormService purchaseFormService;

    public PurchaseFormController(PurchaseFormService purchaseFormService) {
        this.purchaseFormService = purchaseFormService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<PurchaseFormResponse>> createPurchaseForm(@RequestBody PurchaseFormCreateRequest request) {
        PurchaseFormResponse purchaseFormResponse = purchaseFormService.createPurchaseForm(request);
        return new ResponseEntity<>(RestResponse.of(purchaseFormResponse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<PurchaseForm>> getPurchaseFormById(@PathVariable Long id) {
        PurchaseForm purchaseForm = purchaseFormService.getPurchaseFormById(id);
        return new ResponseEntity<>(RestResponse.of(purchaseForm),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<PurchaseFormResponse>>> getAllPurchaseForm() {
        List<PurchaseFormResponse> purchaseFormResponseList = purchaseFormService.getAllPurchaseForm();
        return new ResponseEntity<>(RestResponse.of(purchaseFormResponseList), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<PurchaseFormResponse>> updatePurchaseForm(@PathVariable Long id,@RequestBody PurchaseFormCreateRequest request) {
        PurchaseFormResponse purchaseFormResponse = purchaseFormService.updatePurchaseForm(id,request);
        return new ResponseEntity<>(RestResponse.of(purchaseFormResponse), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestResponse<PurchaseFormResponse>> deletePurchaseForm(@PathVariable Long id) {
        PurchaseFormResponse purchaseFormResponse = purchaseFormService.deletePurchaseForm(id);
        return new ResponseEntity<>(RestResponse.of(purchaseFormResponse), HttpStatus.OK);
    }

}
