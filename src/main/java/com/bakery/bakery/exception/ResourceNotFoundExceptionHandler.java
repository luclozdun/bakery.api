package com.bakery.bakery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceNotFoundExceptionHandler {
    @ExceptionHandler(value = { ResourceNotFoundExceptionRequest.class })
    public ResponseEntity<Object> handlerResponseException(ResourceNotFoundExceptionRequest request){

        var status = HttpStatus.BAD_REQUEST;

        ResourceNotFoundException exception = new ResourceNotFoundException(request.getMessage(), status);
        return new ResponseEntity<>(exception, status);
    }
}
