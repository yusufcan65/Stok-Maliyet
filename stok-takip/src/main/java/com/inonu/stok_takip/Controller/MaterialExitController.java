package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.MaterialExitService;
import com.inonu.stok_takip.dto.Request.MaterialExitCreateRequest;
import com.inonu.stok_takip.dto.Response.MaterialExitResponse;
import com.inonu.stok_takip.dto.Response.RestResponse;
import com.inonu.stok_takip.entitiy.MaterialExit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/materialExit")
public class MaterialExitController {

   private final MaterialExitService materialExitService;

    public MaterialExitController(MaterialExitService materialExitService) {
        this.materialExitService = materialExitService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<List<MaterialExitResponse>>> createMaterialExit(@RequestBody MaterialExitCreateRequest request) {
        List<MaterialExitResponse> materialExitResponse = materialExitService.createMaterialExit(request);
        return new ResponseEntity<>(RestResponse.of(materialExitResponse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<MaterialExit>> getMaterialExitById(@PathVariable Long id) {
        MaterialExit materialExit = materialExitService.getMaterialExitById(id);
        return new ResponseEntity<>(RestResponse.of(materialExit),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<MaterialExitResponse>>> getAllMaterialExits() {
        List<MaterialExitResponse> materialExitResponses = materialExitService.getAllMaterialExits();
        return new ResponseEntity<>(RestResponse.of(materialExitResponses), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<MaterialExitResponse>> updateMaterialExit(@PathVariable Long id, @RequestBody MaterialExitCreateRequest request) {
        MaterialExitResponse materialExitResponse = materialExitService.updateMaterialExit(request);
        return new ResponseEntity<>(RestResponse.of(materialExitResponse), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestResponse<MaterialExitResponse>> deleteMaterialExit(@PathVariable Long id) {
        MaterialExitResponse materialExitResponse = materialExitService.deleteMaterialExit(id);
        return new ResponseEntity<>(RestResponse.of(materialExitResponse), HttpStatus.OK);
    }
}
