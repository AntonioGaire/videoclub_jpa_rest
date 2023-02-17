package org.iesvdm.videoclub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;

@ControllerAdvice
public class CategoriaNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CategoriaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CategoriaNotFoundHandler(CategoriaNotFoundException categoriaNotFoundException) {
        return categoriaNotFoundException.getMessage();
    }
}
