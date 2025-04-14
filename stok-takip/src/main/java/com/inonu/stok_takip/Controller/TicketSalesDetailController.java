package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.TicketSalesDetailService;
import com.inonu.stok_takip.dto.Request.TicketSalesDetailCreateRequest;
import com.inonu.stok_takip.dto.Response.RestResponse;
import com.inonu.stok_takip.dto.Response.TicketSalesDetailResponse;
import com.inonu.stok_takip.entitiy.TicketSalesDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ticketSalesDetail")
public class TicketSalesDetailController {
/*
 private final TicketSalesDetailService ticketSalesDetailService;

    public TicketSalesDetailController(TicketSalesDetailService ticketSalesDetailService) {
        this.ticketSalesDetailService = ticketSalesDetailService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<TicketSalesDetailResponse>> createTicketSalesDetail(@RequestBody TicketSalesDetailCreateRequest request) {
        TicketSalesDetailResponse ticketSalesDetailResponse = ticketSalesDetailService.createTicketSalesDetail(request);
        return new ResponseEntity<>(RestResponse.of(ticketSalesDetailResponse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<TicketSalesDetail>> getTicketSalesDetailById(@PathVariable Long id) {
        TicketSalesDetail ticketSalesDetail = ticketSalesDetailService.getTicketSalesDetailById(id);
        return new ResponseEntity<>(RestResponse.of(ticketSalesDetail),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<TicketSalesDetailResponse>>> getAllTicketSalesDetails() {
        List<TicketSalesDetailResponse> ticketSalesDetailResponseList = ticketSalesDetailService.getAllTicketSalesDetails();
        return new ResponseEntity<>(RestResponse.of(ticketSalesDetailResponseList), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<TicketSalesDetailResponse>> updateTicketSalesDetail(@PathVariable Long id, @RequestBody TicketSalesDetailCreateRequest request) {
        TicketSalesDetailResponse ticketSalesDetailResponse = ticketSalesDetailService.updateTicketSalesDetail(request);
        return new ResponseEntity<>(RestResponse.of(ticketSalesDetailResponse), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestResponse<TicketSalesDetailResponse>> deleteTicketSalesDetail(@PathVariable Long id) {
        TicketSalesDetailResponse ticketSalesDetailResponse = ticketSalesDetailService.deleteTicketSalesDetail(id);
        return new ResponseEntity<>(RestResponse.of(ticketSalesDetailResponse), HttpStatus.OK);
    }*/
}
