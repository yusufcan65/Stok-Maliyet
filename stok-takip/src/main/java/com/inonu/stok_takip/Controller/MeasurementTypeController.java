package com.inonu.stok_takip.Controller;

import com.inonu.stok_takip.Service.MeasurementTypeService;
import com.inonu.stok_takip.dto.Request.MeasurementTypeCreateRequest;
import com.inonu.stok_takip.dto.Response.MeasurementTypeResponse;
import com.inonu.stok_takip.dto.Response.RestResponse;
import com.inonu.stok_takip.entitiy.MeasurementType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/measurementType")
public class MeasurementTypeController {

    private final MeasurementTypeService measurementTypeService;

    public MeasurementTypeController(MeasurementTypeService measurementTypeService) {
        this.measurementTypeService = measurementTypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<MeasurementTypeResponse>> createMeasurementType(@RequestBody MeasurementTypeCreateRequest measurementTypeCreateRequest){
        MeasurementTypeResponse measurementTypeResponse = measurementTypeService.createMeasurementType(measurementTypeCreateRequest);
        return new ResponseEntity<>(RestResponse.of(measurementTypeResponse), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<MeasurementType>> getMeasurementTypeById(@PathVariable("id") Long id){
        MeasurementType measurementType = measurementTypeService.getMeasurementTypeById(id);
        return new ResponseEntity<>(RestResponse.of(measurementType), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<RestResponse<List<MeasurementTypeResponse>>> getAllMeasurementType(){
        List<MeasurementTypeResponse> measurementTypeResponses = measurementTypeService.getAllMeasurementTypes();
        return new ResponseEntity<>(RestResponse.of(measurementTypeResponses), HttpStatus.OK);
    }

}
