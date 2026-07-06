package com.narus.crudcourse.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorResponseFactory factory;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(
            BusinessException ex,
            HttpServletRequest request) {

        return ResponseEntity.badRequest().body(

                factory.build(
                        HttpStatus.BAD_REQUEST,
                        ex.getErrorCode(),
                        ex.getMessage(),
                        List.of(),
                        request
                )

        );

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex,
            HttpServletRequest request) {

        return ResponseEntity.internalServerError().body(

                factory.build(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "An unexpected error has occurred.",
                        List.of(),
                        request
                )

        );

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<ErrorField> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorField(
                        error.getField(),
                        error.getDefaultMessage()))
                .toList();

        ErrorResponse response = factory.build(
                HttpStatus.BAD_REQUEST,
                ErrorCode.VALIDATION_ERROR,
                "Request has errors validation",
                errors,
                request
        );

        return ResponseEntity.badRequest().body(response);
    }

}
