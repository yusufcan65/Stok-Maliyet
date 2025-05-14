package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.MaterialExitService;
import com.inonu.stok_takip.dto.Request.DateRequest;
import com.inonu.stok_takip.dto.Request.MaterialExitCreateRequest;
import com.inonu.stok_takip.dto.Response.MaterialExitDetailResponse;
import com.inonu.stok_takip.dto.Response.MaterialExitResponse;
import com.inonu.stok_takip.dto.Response.RestResponse;
import com.inonu.stok_takip.entitiy.MaterialExit;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/materialExit")
public class MaterialExitController {

   private final MaterialExitService materialExitService;

    public MaterialExitController(MaterialExitService materialExitService) {
        this.materialExitService = materialExitService;
    }


    @PostMapping("/exit")
    public ResponseEntity<RestResponse<List<MaterialExitResponse>>> exitMaterialInStock(@RequestBody MaterialExitCreateRequest request){
        List<MaterialExitResponse> materialExitResponses = materialExitService.exitMaterials(request);
        return new ResponseEntity<>(RestResponse.of(materialExitResponses), HttpStatus.OK);
    }

    @GetMapping("/byMonth")
    public ResponseEntity<RestResponse<Double>> getTotalAmountByMonth(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        Double totalAmount = materialExitService.getMaterialsByMonthAndYear(date);
        return new ResponseEntity<>(RestResponse.of(totalAmount), HttpStatus.OK);
    }
    @GetMapping("/byYear")
    public ResponseEntity<RestResponse<Double>> getTotalAmountByYear(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        Double totalAmount = materialExitService.getMaterialsByYear(date);
        return new ResponseEntity<>(RestResponse.of(totalAmount), HttpStatus.OK);
    }
    @GetMapping("/byDay")
    public ResponseEntity<RestResponse<Double>> getTotalAmountByDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        Double totalAmount = materialExitService.getNonCleaningMaterialExitsByDate(date);
        return new ResponseEntity<>(RestResponse.of(totalAmount), HttpStatus.OK);
    }
    @GetMapping("/totalPersonByDay")
    public ResponseEntity<RestResponse<Integer>> totalPersonByDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        Integer totalPerson = materialExitService.numberMealsInDay(date);
        return new ResponseEntity<>(RestResponse.of(totalPerson), HttpStatus.OK);
    }
    @GetMapping("/totalPersonByMonth")
    public ResponseEntity<RestResponse<Integer>> totalPersonByMonth(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        Integer totalPerson = materialExitService.numberMealsInMonth(date);
        return new ResponseEntity<>(RestResponse.of(totalPerson), HttpStatus.OK);
    }
    @GetMapping("/totalPersonByYear")
    public ResponseEntity<RestResponse<Integer>> totalPersonByYear(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        Integer totalPerson = materialExitService.numberMealsInYear(date);
        return new ResponseEntity<>(RestResponse.of(totalPerson), HttpStatus.OK);
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

    @GetMapping("/getMaterialExitBetweenDates")
    public ResponseEntity<RestResponse<List<MaterialExitDetailResponse>>> getMaterialExitBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        DateRequest request = new DateRequest(startDate, endDate);
        List<MaterialExitDetailResponse> responses = materialExitService.getMaterialExitBetweenDates(request);
        return ResponseEntity.ok(RestResponse.of(responses));
    }
 }
