package com.zup.zupProgress.exceptionHandler;

import com.zup.zupProgress.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handlingOfDuplicateValues(ConstraintViolationException constraintViolationException){
        ExceptionDTO exceptionResponse = new ExceptionDTO(new Date(),"Dados inserido incorretamente");
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handlingOf404(EntityNotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(new Date(),exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }
    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity handlingOfIncorrectDates(DateTimeException exception){
        ExceptionDTO exceptionResponse = new ExceptionDTO(new Date(),exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity handlingOfExistsEmail(EmailAlreadyExistsException emailAlreadyExistsException){
        ExceptionDTO exceptionDTO = new ExceptionDTO(new Date(),emailAlreadyExistsException.getMessage());
        return ResponseEntity.status(409).body(exceptionDTO);
    }
}
