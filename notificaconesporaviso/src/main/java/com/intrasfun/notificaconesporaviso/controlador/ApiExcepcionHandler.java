package com.intrasfun.notificaconesporaviso.controlador;

import com.intrasfun.notificaconesporaviso.dto.RespuestaExcepcionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExcepcionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<RespuestaExcepcionDTO> noEncontroHandle(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RespuestaExcepcionDTO(exception.getMessage()));
    }
}
