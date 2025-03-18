package com.ifscsal.clinicaodontologica.clinicaodotologica.infra;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class RequestExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFound(){
        StatusErroMessage status = new StatusErroMessage(HttpStatus.NOT_FOUND, "Servidor não encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolation() {
        StatusErroMessage status = new StatusErroMessage(HttpStatus.BAD_REQUEST, "Inserção de dados inválidas!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(status);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrityViolation(){
        StatusErroMessage status = new StatusErroMessage(HttpStatus.CONFLICT, "Dados duplicados ou Referencias invalidas");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception e){
        StatusErroMessage status = new StatusErroMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
    }
}
