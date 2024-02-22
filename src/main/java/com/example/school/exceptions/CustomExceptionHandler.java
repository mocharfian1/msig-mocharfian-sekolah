package com.example.school.exceptions;

import com.example.school.model.entity.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestControllerAdvice
class CustomExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleInvalidArgument(MethodArgumentNotValidException exception)
    {
        String error = exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new Response(HttpStatus.BAD_REQUEST.value(), error, null));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Response> handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Response(HttpStatus.BAD_REQUEST.value(), "Parameter " + ex.getRequestPartName() + " tidak ditemukan", null));
    }
}
