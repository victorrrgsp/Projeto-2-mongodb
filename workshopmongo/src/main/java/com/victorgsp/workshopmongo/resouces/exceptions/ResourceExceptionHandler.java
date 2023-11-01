package com.victorgsp.workshopmongo.resouces.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.victorgsp.workshopmongo.services.exceptions.ObjectNotFoundExcetion;

import jakarta.servlet.http.HttpServletRequest;

// esse ControllerAdvice diz que essa class é responsavel por tratar possiveis erros nas minhas requisições
@ControllerAdvice
public class ResourceExceptionHandler {

    // vai funcionar quando ocorrer a exceção ObjectNotFoundExcetion, vai fazer o tratamento que estar no metodo
    @ExceptionHandler(ObjectNotFoundExcetion.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundExcetion e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
