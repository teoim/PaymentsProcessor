package com.rftech.payments.processor.exception;

import com.rftech.payments.processor.controller.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ControllerAdvice
public class PaymentsApiGlobalExceptionHandler {

    @ExceptionHandler(value = InvalidPaymentIdException.class)
    protected ResponseEntity<ErrorDTO> handleInvalidPaymentIdException(
            InvalidPaymentIdException ex
            , WebRequest request){

        ErrorDTO errorObject = new ErrorDTO();
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setTimestamp( Timestamp.valueOf(LocalDateTime.now()));
        errorObject.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PaymentNotFoundException.class)
    protected ResponseEntity<ErrorDTO> handlePaymentNotFoundException(
            PaymentNotFoundException ex,
            WebRequest request){

        ErrorDTO errorObject = new ErrorDTO(
                HttpStatus.NOT_FOUND.value()
                , ex.getMessage()
                , Timestamp.valueOf(LocalDateTime.now()));

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }
}
