package com.inonu.stok_takip.Exception;

import com.inonu.stok_takip.Exception.MeasurementType.MeasurementTypeNotFoundException;
import com.inonu.stok_takip.dto.Response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(MeasurementTypeNotFoundException.class)
    public ResponseEntity<RestResponse<String>> measurementTypeNotFoundException(MeasurementTypeNotFoundException exception) {
        return new ResponseEntity<>(RestResponse.error(exception.getMessage()), HttpStatus.NOT_FOUND);

    }
}
