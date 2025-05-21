package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.DirectProcurementService;
import com.inonu.stok_takip.dto.Request.DirectProcurementCreateRequest;
import com.inonu.stok_takip.dto.Response.DirectProcurementResponse;
import com.inonu.stok_takip.dto.Response.RestResponse;
import com.inonu.stok_takip.dto.Response.TenderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/directProcurement")
public class DirectProcurementController {

    private final DirectProcurementService directProcurementService;

    public DirectProcurementController(DirectProcurementService directProcurementService) {
        this.directProcurementService = directProcurementService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<DirectProcurementResponse>> createDirectProcurement(@RequestBody DirectProcurementCreateRequest request){
        DirectProcurementResponse directProcurementResponse = directProcurementService.createDirectProcurement(request);
        return new ResponseEntity<>(RestResponse.of(directProcurementResponse), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<DirectProcurementResponse>>> getAllDirectProcurement(){
        List<DirectProcurementResponse> directProcurementResponses = directProcurementService.getAllDirectProcurements();
        return new ResponseEntity<>(RestResponse.of(directProcurementResponses), HttpStatus.OK);
    }

    @GetMapping("/activeDirectProcurement")
    public ResponseEntity<RestResponse<List<DirectProcurementResponse>>> getActiveDirectProcurements(){
        List<DirectProcurementResponse> directProcurementResponses = directProcurementService.getAllActiveDirectProcurements();
        return new ResponseEntity<>(RestResponse.of(directProcurementResponses), HttpStatus.OK);
    }

    @PostMapping("/increaseDirectProcurement/{directProcurementId}/{increasedQuantity}")
    public ResponseEntity<RestResponse<DirectProcurementResponse>> increaseDirectProcurement(@PathVariable Long directProcurementId, @PathVariable Double increasedQuantity) {
        DirectProcurementResponse directProcurementResponse = directProcurementService.increaseDirectProcurementByTwentyPercent(directProcurementId,increasedQuantity);
        return new ResponseEntity<>(RestResponse.of(directProcurementResponse), HttpStatus.OK);
    }

    @GetMapping("/getDirectsByCompanyAndProducts")
    public ResponseEntity<RestResponse<List<DirectProcurementResponse>>> getDirectProcurementByCompanyAndProducts(){
        List<DirectProcurementResponse> directProcurementResponses = directProcurementService.getDirectProcurementsByProductAndCompany();
        return new ResponseEntity<>(RestResponse.of(directProcurementResponses), HttpStatus.OK);
    }
}
