package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Enum.DemandStatus;
import com.inonu.stok_takip.Service.MaterialDemandService;
import com.inonu.stok_takip.dto.Request.MaterialDemandApprovedRequest;
import com.inonu.stok_takip.dto.Request.MaterialDemandCreateRequest;
import com.inonu.stok_takip.dto.Request.MaterialDemandUpdateRequest;
import com.inonu.stok_takip.dto.Response.MaterialDemandResponse;
import com.inonu.stok_takip.dto.Response.RestResponse;
import com.inonu.stok_takip.entitiy.MaterialDemand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/materialDemand")
public class MaterialDemandController {

    private final MaterialDemandService materialDemandService;

    public MaterialDemandController(MaterialDemandService materialDemandService) {
        this.materialDemandService = materialDemandService;
    }

    @PostMapping("/approve")
    public ResponseEntity<RestResponse<MaterialDemandResponse>> approveMaterialDemand(@RequestBody MaterialDemandApprovedRequest request) {
        MaterialDemandResponse materialDemandResponse =  materialDemandService.approveAndProcessMaterialDemand(request);
        return new ResponseEntity<>(RestResponse.of(materialDemandResponse), HttpStatus.OK);
    }


    @PostMapping("/reject/{id}")
    public ResponseEntity<RestResponse<MaterialDemandResponse>> updateDemandStatus(
            @PathVariable Long id,
            @RequestParam(required = false) String rejectionReason
    ) {
        MaterialDemandResponse materialDemandResponse =  materialDemandService.rejectMaterialDemand(id, rejectionReason);
        return new ResponseEntity<>(RestResponse.of(materialDemandResponse),HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<RestResponse<MaterialDemandResponse>> createMaterialDemand(@RequestBody MaterialDemandCreateRequest request) {
        MaterialDemandResponse materialDemandResponse = materialDemandService.createMaterialDemand(request);
        return new ResponseEntity<>(RestResponse.of(materialDemandResponse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<MaterialDemand>> getMaterialDemandById(@PathVariable Long id) {
        MaterialDemand materialDemand = materialDemandService.getMaterialDemandById(id);
        return new ResponseEntity<>(RestResponse.of(materialDemand),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<MaterialDemandResponse>>> getAllMaterialDemandList() {
        List<MaterialDemandResponse> materialDemandResponses = materialDemandService.getAllMaterialDemand();
        return new ResponseEntity<>(RestResponse.of(materialDemandResponses), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<RestResponse<MaterialDemandResponse>> updateMaterialDemand( @RequestBody MaterialDemandUpdateRequest request) {
        MaterialDemandResponse materialDemandResponse = materialDemandService.updateMaterialDemand(request);
        return new ResponseEntity<>(RestResponse.of(materialDemandResponse), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestResponse<MaterialDemandResponse>> deleteMaterialDemand(@PathVariable Long id) {
        MaterialDemandResponse materialDemandResponse = materialDemandService.deleteMaterialDemand(id);
        return new ResponseEntity<>(RestResponse.of(materialDemandResponse), HttpStatus.OK);
    }

}
