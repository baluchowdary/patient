package com.kollu.patient.api.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	//Server error
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> detailsList = new ArrayList<>();
        detailsList.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Exceptions", detailsList);
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR); 
    }

	//GET - If requested id is not available
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        List<String> detailsList = new ArrayList<>();
        detailsList.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found Exceptions", detailsList);
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND); 
    }
    
    //input params validation
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Method Args Validation Failed Exception", details);
        return new ResponseEntity<Object>(error, HttpStatusCode.valueOf(400)); 
    }
}
