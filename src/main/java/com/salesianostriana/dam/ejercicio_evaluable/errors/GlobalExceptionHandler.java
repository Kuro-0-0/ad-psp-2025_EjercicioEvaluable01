package com.salesianostriana.dam.ejercicio_evaluable.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoItemsFound.class)
    public ProblemDetail noItemsFound(NoItemsFound ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,ex.getMessage()
        );
        problemDetail.setTitle("Museos no encontrados.");
        return problemDetail;
    }

    @ExceptionHandler(InvalidMuseumDataException.class)
    public ProblemDetail invalidData(InvalidMuseumDataException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        problemDetail.setTitle("Datos incorrectos enviados en el museo.");
        return problemDetail;
    }

    @ExceptionHandler(MuseumNotFoundException.class)
    public ProblemDetail notFound(MuseumNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problemDetail.setTitle("Museo no encontrado.");
        return problemDetail;
    }


}
