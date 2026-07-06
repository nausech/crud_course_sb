package com.narus.crudcourse.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ErrorResponseFactory {

    public ErrorResponse build(
            HttpStatus status,
            ErrorCode code,
            String message,
            List<ErrorField> errors,
            HttpServletRequest request) {

        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                code,
                message,
                errors,
                request.getRequestURI()
        );
    }

}