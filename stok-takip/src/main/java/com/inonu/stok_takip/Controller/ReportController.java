package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Enum.ReportType;
import com.inonu.stok_takip.Service.ReportService;
import com.inonu.stok_takip.dto.Response.ReportResponse;
import com.inonu.stok_takip.dto.Response.RestResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/report")
public class ReportController {

   private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping("/create/{reportType}")
    public ResponseEntity<RestResponse<ReportResponse>> createReport(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @PathVariable ReportType reportType) {
        ReportResponse reportResponse = reportService.createReport(date,reportType);
        return new ResponseEntity<>(RestResponse.of(reportResponse), HttpStatus.OK);
    }



    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<ReportResponse>>> getAllReports() {
        List<ReportResponse> reportResponses = reportService.getAllReports();
        return new ResponseEntity<>(RestResponse.of(reportResponses), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<RestResponse<ReportResponse>> getReportDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                      @RequestParam("reportType") ReportType reportType) {
        ReportResponse reportResponse = reportService.getReportByDate(date,reportType);
        return new ResponseEntity<>(RestResponse.of(reportResponse), HttpStatus.OK);
    }
    @GetMapping("/betweenDate")
    public ResponseEntity<RestResponse<List<ReportResponse>>> getReportsBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<ReportResponse> reportResponses = reportService.getReportsBetweenDate(startDate,endDate);
        return new ResponseEntity<>(RestResponse.of(reportResponses), HttpStatus.OK);
    }

}
