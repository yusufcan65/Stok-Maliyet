package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.DirectProcurementTransactionService;
import com.inonu.stok_takip.dto.Request.DirectProcurementCreateRequest;
import com.inonu.stok_takip.dto.Response.DirectProcurementResponse;
import com.inonu.stok_takip.dto.Response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/directProcurementAndMaterialEntry")
public class DirectProcurementTransactionController {


    private final DirectProcurementTransactionService directProcurementTransactionService;

    public DirectProcurementTransactionController(DirectProcurementTransactionService directProcurementTransactionService) {
        this.directProcurementTransactionService = directProcurementTransactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<DirectProcurementResponse>> createDirectProcurementAndMaterialEntry(@RequestBody DirectProcurementCreateRequest request) {

        DirectProcurementResponse directProcurementResponse = directProcurementTransactionService.createDirectAndMaterialEntry(request);
        return new ResponseEntity<>(RestResponse.of(directProcurementResponse), HttpStatus.OK);
    }
}