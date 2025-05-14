package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.TenderService;
import com.inonu.stok_takip.dto.Request.TenderCreateRequest;
import com.inonu.stok_takip.dto.Response.RestResponse;
import com.inonu.stok_takip.dto.Response.TenderDetailResponse;
import com.inonu.stok_takip.dto.Response.TenderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tender")
public class TenderController {

    private final TenderService tenderService;

    public TenderController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<TenderResponse>> createTender(@RequestBody TenderCreateRequest request) {
        TenderResponse tenderResponse = tenderService.createTender(request);
        return new ResponseEntity<>(RestResponse.of(tenderResponse), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<TenderResponse>>> getAllTender() {
        List<TenderResponse> tenderResponses = tenderService.getAllTenders();
        return new ResponseEntity<>(RestResponse.of(tenderResponses), HttpStatus.OK);
    }
    @PostMapping("/increaseTender/{tenderId}/{increasedQuantity}")
    public ResponseEntity<RestResponse<TenderResponse>> increaseTender(@PathVariable Long tenderId, @PathVariable Double increasedQuantity) {
        TenderResponse tenderResponse = tenderService.increaseTenderByTwentyPercent(tenderId,increasedQuantity);
        return new ResponseEntity<>(RestResponse.of(tenderResponse), HttpStatus.OK);
    }
    @GetMapping("/listByPurchaseFormId/{purchaseFormId}")
    public ResponseEntity<RestResponse<List<TenderResponse>>> getListByPurchaseFormId(@PathVariable Long purchaseFormId) {
        List<TenderResponse> tenderResponseList = tenderService.getTendersByPurchaseForm(purchaseFormId);
        return new ResponseEntity<>(RestResponse.of(tenderResponseList), HttpStatus.OK);
    }
    @GetMapping("/totalAmountByPurchaseId/{purchaseFormId}")
    public ResponseEntity<RestResponse<Double>> totalAmountByPurchaseForm(@PathVariable Long purchaseFormId){
        Double totalAmount = tenderService.calculateTotalAmountByPurchaseFormId(purchaseFormId);
        return new ResponseEntity<>(RestResponse.of(totalAmount), HttpStatus.OK);

    }

    @GetMapping("/getTenderDetailByPurchaseForm")
    public ResponseEntity<RestResponse<List<TenderDetailResponse>>> getTenderDetailByPurchaseForm(){
        List<TenderDetailResponse> tenderDetailResponses = tenderService.getPurchaseFormsWithDetails();
        return new ResponseEntity<>(RestResponse.of(tenderDetailResponses), HttpStatus.OK);
    }

    @GetMapping("/getTendersByProductsAndCompany")
    public ResponseEntity<RestResponse<List<TenderResponse>>> getTendersByProductAndCompany(){
        List<TenderResponse> tenderResponses = tenderService.getTenderByProductAndCompany();
        return new ResponseEntity<>(RestResponse.of(tenderResponses),HttpStatus.OK);
    }
}
