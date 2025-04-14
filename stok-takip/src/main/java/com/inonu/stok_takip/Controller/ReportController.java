package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.ReportService;
import com.inonu.stok_takip.dto.Request.ReportCreateRequest;
import com.inonu.stok_takip.dto.Response.ReportResponse;
import com.inonu.stok_takip.dto.Response.RestResponse;
import com.inonu.stok_takip.entitiy.Report;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/report")
public class ReportController {

    /*private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping("/create")
    public ResponseEntity<RestResponse<ReportResponse>> createReport(@RequestBody ReportCreateRequest request) {
        ReportResponse reportResponse = reportService.createReport(request);
        return new ResponseEntity<>(RestResponse.of(reportResponse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Report>> getReportById(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        return new ResponseEntity<>(RestResponse.of(report),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<ReportResponse>>> getAllReports() {
        List<ReportResponse> reportResponses = reportService.getAllReports();
        return new ResponseEntity<>(RestResponse.of(reportResponses), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<ReportResponse>> updateReport(@PathVariable Long id, @RequestBody ReportCreateRequest request) {
        ReportResponse reportResponse = reportService.updateReport(request);
        return new ResponseEntity<>(RestResponse.of(reportResponse), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestResponse<ReportResponse>> deleteReport(@PathVariable Long id) {
        ReportResponse reportResponse = reportService.deleteReport(id);
        return new ResponseEntity<>(RestResponse.of(reportResponse), HttpStatus.OK);
    }
*/
}
