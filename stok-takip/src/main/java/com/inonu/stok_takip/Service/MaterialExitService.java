package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.DateRequest;
import com.inonu.stok_takip.dto.Request.MaterialExitCreateRequest;
import com.inonu.stok_takip.dto.Response.MaterialExitResponse;
import com.inonu.stok_takip.entitiy.MaterialExit;

import java.util.List;

public interface MaterialExitService {

    List<MaterialExitResponse> getAllMaterialExits();
   // List<MaterialExitResponse> createMaterialExit(MaterialExitCreateRequest request);
    MaterialExitResponse updateMaterialExit(MaterialExitCreateRequest request);
    MaterialExitResponse deleteMaterialExit(Long id);
    MaterialExit getMaterialExitById(Long id);

    // bundan sonrası rapor ve fiş yapısını kntrol etmek ve denemek için eklnmiştir
    List<MaterialExitResponse> getMaterialListBetweenDate(DateRequest dateRequest);
    int numberMealsBetweenDates(DateRequest dateRequest);
    Double calculateTotalAmount(DateRequest dateRequest);
    Double calculateCleanMaterialPrice(DateRequest dateRequest);
    List<MaterialExitResponse> exitMaterials(MaterialExitCreateRequest request);

}
