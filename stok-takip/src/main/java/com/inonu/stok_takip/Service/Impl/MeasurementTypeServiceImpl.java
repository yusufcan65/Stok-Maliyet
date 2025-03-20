package com.inonu.stok_takip.Service.Impl;


import com.inonu.stok_takip.Exception.MeasurementType.MeasurementTypeNotFoundException;
import com.inonu.stok_takip.Repositoriy.MeasurementTypeRepository;
import com.inonu.stok_takip.Service.MeasurementTypeService;
import com.inonu.stok_takip.dto.Request.CreateMeasurementTypeRequest;
import com.inonu.stok_takip.dto.Response.MeasurementTypeResponse;
import com.inonu.stok_takip.entitiy.MeasurementType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementTypeServiceImpl implements MeasurementTypeService {

    private final MeasurementTypeRepository measurementTypeRepository;

    public MeasurementTypeServiceImpl(MeasurementTypeRepository measurementTypeRepository) {
        this.measurementTypeRepository = measurementTypeRepository;
    }


    @Override
    public MeasurementTypeResponse createMeasurementType(CreateMeasurementTypeRequest createMeasurementTypeRequest) {
        MeasurementType measurementType = new MeasurementType();
        measurementType.setName(createMeasurementTypeRequest.name());
        MeasurementType saved = measurementTypeRepository.save(measurementType);
        return mapToResponse(saved);
    }

    @Override
    public MeasurementType getMeasurementTypeById(Long id) {
        return measurementTypeRepository.findById(id).orElseThrow(()-> new MeasurementTypeNotFoundException("Measurement type not found"+id));

    }

    @Override
    public List<MeasurementTypeResponse> getAllMeasurementTypes() {
        List<MeasurementType> measurementTypes = measurementTypeRepository.findAll();
        return mapToResponseList(measurementTypes);
    }

    @Override
    public MeasurementTypeResponse deleteMeasurementType(Long id) {
        MeasurementType measurementType = getMeasurementTypeById(id);
        measurementTypeRepository.delete(measurementType);
        return mapToResponse(measurementType);
    }

    private MeasurementTypeResponse mapToResponse(MeasurementType measurementType) {
        MeasurementTypeResponse measurementTypeResponse = new MeasurementTypeResponse(measurementType.getId(),measurementType.getName());
        return measurementTypeResponse;
    }

    private List<MeasurementTypeResponse> mapToResponseList(List<MeasurementType> measurementTypes) {
        List<MeasurementTypeResponse> measurementTypeResponses = measurementTypes.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return measurementTypeResponses;

    }


}
