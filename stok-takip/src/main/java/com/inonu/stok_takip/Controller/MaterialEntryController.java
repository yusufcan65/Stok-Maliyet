package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.MaterialEntryService;
import com.inonu.stok_takip.dto.Request.DateRequest;
import com.inonu.stok_takip.dto.Request.MaterialEntryCreateRequest;
import com.inonu.stok_takip.dto.Request.MaterialEntryUpdateRequest;
import com.inonu.stok_takip.dto.Response.MaterialEntryResponse;
import com.inonu.stok_takip.dto.Response.RestResponse;
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

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<MaterialEntryResponse>> updateMaterialEntry(@PathVariable Long id, @RequestBody MaterialEntryUpdateRequest request) {
        MaterialEntryResponse materialEntryResponse = materialEntryService.updateMaterialEntry(request);
        return new ResponseEntity<>(RestResponse.of(materialEntryResponse), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestResponse<MaterialEntryResponse>> deleteMaterialEntry(@PathVariable Long id) {
        MaterialEntryResponse materialEntryResponse = materialEntryService.deleteMaterialEntry(id);
        return new ResponseEntity<>(RestResponse.of(materialEntryResponse), HttpStatus.OK);
    }
    // devir eden api
    @PostMapping("/cede")
    public ResponseEntity<RestResponse<List<MaterialEntryResponse>>> carryOverEntriesToNextYear(@RequestBody DateRequest dateRequest){
        List<MaterialEntryResponse> materialEntryResponses = materialEntryService.carryOverEntriesToNextYear(dateRequest);
        return new ResponseEntity<>(RestResponse.of(materialEntryResponses), HttpStatus.OK);
    }
}
