package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.MaterialEntryService;
import com.inonu.stok_takip.dto.Request.MaterialEntryCreateRequest;
import com.inonu.stok_takip.dto.Request.MaterialEntryUpdateRequest;
import com.inonu.stok_takip.dto.Response.*;
import com.inonu.stok_takip.entitiy.MaterialEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/materialEntry")
public class MaterialEntryController {

    private final MaterialEntryService materialEntryService;

    public MaterialEntryController(MaterialEntryService materialEntryService) {
        this.materialEntryService = materialEntryService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<MaterialEntryResponse>> createMaterialEntry(@RequestBody MaterialEntryCreateRequest request) {
        MaterialEntryResponse materialEntryResponse = materialEntryService.createMaterialEntry(request);
        return new ResponseEntity<>(RestResponse.of(materialEntryResponse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<MaterialEntry>> getMaterialEntryById(@PathVariable Long id) {
        MaterialEntry materialEntry = materialEntryService.getMaterialEntryById(id);
        return new ResponseEntity<>(RestResponse.of(materialEntry),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<MaterialEntryResponse>>> getAllMaterialEntryList() {
        List<MaterialEntryResponse> materialEntryResponses = materialEntryService.getAllMaterialEntryList();
        return new ResponseEntity<>(RestResponse.of(materialEntryResponses), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<RestResponse<MaterialEntryResponse>> updateMaterialEntry(@RequestBody MaterialEntryUpdateRequest request) {
        MaterialEntryResponse materialEntryResponse = materialEntryService.updateMaterialEntry(request);
        return new ResponseEntity<>(RestResponse.of(materialEntryResponse), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestResponse<MaterialEntryResponse>> deleteMaterialEntry(@PathVariable Long id) {
        MaterialEntryResponse materialEntryResponse = materialEntryService.deleteMaterialEntry(id);
        return new ResponseEntity<>(RestResponse.of(materialEntryResponse), HttpStatus.OK);
    }
    // devir işlemi yapan api
    @PostMapping("/carry-over")
    public ResponseEntity<RestResponse<List<MaterialEntryResponse>>> carryOverEntriesToNextYear(){
        List<MaterialEntryResponse> materialEntryResponses = materialEntryService.carryOverEntriesToNextYear();
        return new ResponseEntity<>(RestResponse.of(materialEntryResponses), HttpStatus.OK);
    }


    //bu metot yıl içinde depoya giren tüm malzemeler ve nasıl girdikleri ile ilgili bilgileir döndürür
    @GetMapping("/getAllDetail")
    public ResponseEntity<RestResponse<List<MaterialEntryDetailResponse>>> getAllDetail() {
        List<MaterialEntryDetailResponse> materialEntryDetailResponses = materialEntryService.getMaterialEntryDetails();
        return new ResponseEntity<>(RestResponse.of(materialEntryDetailResponses), HttpStatus.OK);
    }

    // anasayfada yer alan malzemelerr buradan gönderiliyor
    @GetMapping("/getAllProductDetail")
    public ResponseEntity<RestResponse<List<GroupMaterialEntryResponse>>> getGroupedMaterialEntries( ){
        List<GroupMaterialEntryResponse> groupedMaterialEntryResponses = materialEntryService.getGroupedMaterialEntries();
        return new ResponseEntity<>(RestResponse.of(groupedMaterialEntryResponses), HttpStatus.OK);
    }
    // bütçelere göre gruplama
    @GetMapping("/getBudgetsGroup")
    public ResponseEntity<RestResponse<List<MaterialEntrySpendResponse>>> getSpendsInYear(){
        List<MaterialEntrySpendResponse> materialEntrySpendResponses = materialEntryService.getTotalSpentGroupedByBudget();
        return new ResponseEntity<>(RestResponse.of(materialEntrySpendResponses), HttpStatus.OK);
    }

    // malzeme çıkışı yapılacağı zaman depoda yer alan ürünleri ve ne kadar kaldığını sunar
    @GetMapping("/getMaterialsByMaterialExit")
    public ResponseEntity<RestResponse<List<MaterialEntryProductsForMaterialExitResponse>>> getMaterialsByMaterialExit(){
        List<MaterialEntryProductsForMaterialExitResponse> materialEntryProductsForMaterialExitResponses = materialEntryService.getMaterialEntriesForExit();
        return new ResponseEntity<>(RestResponse.of(materialEntryProductsForMaterialExitResponses), HttpStatus.OK);
    }
 }
