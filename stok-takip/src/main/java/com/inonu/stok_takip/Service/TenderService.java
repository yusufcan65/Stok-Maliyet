package com.inonu.stok_takip.Service;

import com.inonu.stok_takip.dto.Request.TenderCreateRequest;
import com.inonu.stok_takip.dto.Response.TenderDetailResponse;
import com.inonu.stok_takip.dto.Response.TenderResponse;
import com.inonu.stok_takip.entitiy.Tender;

import java.util.List;

public interface TenderService {

    TenderResponse createTender(TenderCreateRequest request);
    List<TenderResponse> getAllTenders();
    TenderResponse updateTenderRemainingQuantity(Long tenderId, Double quantity);
    Tender getTenderById(Long id);
    TenderResponse increaseTenderByTwentyPercent(Long tenderId);
    TenderResponse deleteTender(Long id);
    TenderResponse updateTender(TenderCreateRequest request);
    void handleTendersAtYearEnd();

    // bu kod purchase form değerine göre tenderleri listeler
    List<TenderResponse> getTendersByPurchaseForm(Long purchaseFormId);
    Double calculateTotalAmountByPurchaseFormId(Long purchaseFormId);

    List<TenderDetailResponse> getPurchaseFormsWithDetails();

    List<TenderResponse> getTenderByProductAndCompany();
}
