package com.inonu.stok_takip.Exception;

import com.inonu.stok_takip.Exception.Budget.BudgetNotFoundException;
import com.inonu.stok_takip.Exception.Category.CategoryNotFoundException;
import com.inonu.stok_takip.Exception.DirectProcurement.DirectProcurementAlreadyIncreasedException;
import com.inonu.stok_takip.Exception.DirectProcurement.DirectProcurementNotFoundException;
import com.inonu.stok_takip.Exception.MaterialDemand.InvalidMaterialDemandOperationException;
import com.inonu.stok_takip.Exception.MaterialDemand.MaterialDemandNotFoundException;
import com.inonu.stok_takip.Exception.MaterialEntry.MaterialEntryNotFoundException;
import com.inonu.stok_takip.Exception.MaterialEntry.ProductOutOfStockException;
import com.inonu.stok_takip.Exception.MaterialEntry.StockNotAvailableException;
import com.inonu.stok_takip.Exception.MaterialExit.InsufficientStockException;
import com.inonu.stok_takip.Exception.MaterialExit.MaterialExitNotFoundException;
import com.inonu.stok_takip.Exception.MeasurementType.MeasurementTypeNotFoundException;
import com.inonu.stok_takip.Exception.Product.ProductNotFoundException;
import com.inonu.stok_takip.Exception.PurchaseForm.PurchaseFormNotFoundException;
import com.inonu.stok_takip.Exception.PurchaseType.PurchaseTypeAlreadyExistsException;
import com.inonu.stok_takip.Exception.PurchaseType.PurchaseTypeNotFoundException;
import com.inonu.stok_takip.Exception.PurchasedUnit.PurchasedUnitNotFoundException;
import com.inonu.stok_takip.Exception.Report.ReportDataNotFoundException;
import com.inonu.stok_takip.Exception.Tender.TenderAlreadyIncreasedException;
import com.inonu.stok_takip.Exception.Tender.TenderNotFoundException;
import com.inonu.stok_takip.Exception.TicketType.TicketTypeNotFoundException;
import com.inonu.stok_takip.dto.Response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BudgetNotFoundException.class)
    public ResponseEntity<RestResponse<String>> budgetNotFoundException(BudgetNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<RestResponse<String>> categoryNotFoundException(CategoryNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaterialDemandNotFoundException.class)
    public ResponseEntity<RestResponse<String>> materialDemandNotFoundException(MaterialDemandNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidMaterialDemandOperationException.class)
    public ResponseEntity<RestResponse<String>> invalidMaterialDemandOperationException(InvalidMaterialDemandOperationException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaterialEntryNotFoundException.class)
    public ResponseEntity<RestResponse<String>> materialEntryNotFoundException(MaterialEntryNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductOutOfStockException.class)
    public ResponseEntity<RestResponse<String>> productOutOfStockException(ProductOutOfStockException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StockNotAvailableException.class)
    public ResponseEntity<RestResponse<String>> stockNotAvailableException(StockNotAvailableException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaterialExitNotFoundException.class)
    public ResponseEntity<RestResponse<String>> materialExitNotFoundException(MaterialDemandNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<RestResponse<String>> insufficientStockException(InsufficientStockException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MeasurementTypeNotFoundException.class)
    public ResponseEntity<RestResponse<String>> measurementTypeNotFoundException(MeasurementTypeNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<RestResponse<String>> productNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PurchasedUnitNotFoundException.class)
    public ResponseEntity<RestResponse<String>> purchasedUnitNotFoundException(PurchasedUnitNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PurchaseFormNotFoundException.class)
    public ResponseEntity<RestResponse<String>> purchaseFormNotFoundException(PurchaseFormNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PurchaseTypeNotFoundException.class)
    public ResponseEntity<RestResponse<String>> purchaseTypeNotFoundException(PurchaseTypeNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketTypeNotFoundException.class)
    public ResponseEntity<RestResponse<String>> ticketTypeNotFoundException(TicketTypeNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TenderAlreadyIncreasedException.class)
    public ResponseEntity<RestResponse<String>> TenderAlreadyIncreasedException(TenderAlreadyIncreasedException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TenderNotFoundException.class)
    public ResponseEntity<RestResponse<String>> TenderNotFoundException(TenderNotFoundException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PurchaseTypeAlreadyExistsException.class)
    public ResponseEntity<RestResponse<String>> PurchaseTypeAlreadyExistsException(PurchaseTypeAlreadyExistsException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReportDataNotFoundException.class)
    public ResponseEntity<RestResponse<String>> reportDataNotFoundException(ReportDataNotFoundException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DirectProcurementNotFoundException.class)
    public ResponseEntity<RestResponse<String>> directProcurementNotFoundException(DirectProcurementNotFoundException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DirectProcurementAlreadyIncreasedException.class)
    public ResponseEntity<RestResponse<String>> directProcurementAlreadyIncreasedException(DirectProcurementAlreadyIncreasedException exception){
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
