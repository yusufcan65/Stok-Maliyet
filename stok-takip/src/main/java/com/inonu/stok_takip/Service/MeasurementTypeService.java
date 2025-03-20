package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.CreateMeasurementTypeRequest;
import com.inonu.stok_takip.dto.Response.MeasurementTypeResponse;
import com.inonu.stok_takip.entitiy.MeasurementType;

import java.util.List;

public interface MeasurementTypeService {

    MeasurementTypeResponse createMeasurementType(CreateMeasurementTypeRequest createMeasurementTypeRequest);
    MeasurementType getMeasurementTypeById(Long id);
    List<MeasurementTypeResponse> getAllMeasurementTypes();
    MeasurementTypeResponse deleteMeasurementType(Long id);
}
