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
        return toResponse(saved);
    }

    @Override
    public MeasurementTypeResponse getMeasurementTypeById(Long id) {
        MeasurementType measurementType =  measurementTypeRepository.findById(id).orElseThrow(()-> new MeasurementTypeNotFoundException("Measurement type not found"+id));
        return toResponse(measurementType);
    }

    @Override
    public List<MeasurementTypeResponse> getAll() {
        List<MeasurementType> measurementTypes = measurementTypeRepository.findAll();
        return toResponseList(measurementTypes);
    }

    @Override
    public void delete(Long id) {
        measurementTypeRepository.deleteById(id);
    }

    @Override
    public MeasurementTypeResponse updateMeasurementType(CreateMeasurementTypeRequest createMeasurementTypeRequest) {
        return null;
    }

    private MeasurementTypeResponse toResponse(MeasurementType measurementType) {
        MeasurementTypeResponse measurementTypeResponse = new MeasurementTypeResponse(measurementType.getId(),measurementType.getName());
        return measurementTypeResponse;
    }

    private List<MeasurementTypeResponse> toResponseList(List<MeasurementType> measurementTypes) {
        List<MeasurementTypeResponse> measurementTypeResponses = measurementTypes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return measurementTypeResponses;

    }

    /* private MeasurementType toEntity(MeasurementTypeResponse measurementTypeResponse) {
        MeasurementType measurementType = new MeasurementType();
        measurementType.setId(measurementTypeResponse.getId());
        measurementType.setName(measurementTypeResponse.getName());
        return measurementType;
    }*/

}
