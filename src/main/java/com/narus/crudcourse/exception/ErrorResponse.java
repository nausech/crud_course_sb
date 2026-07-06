package com.narus.crudcourse.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(

        LocalDateTime timestamp,
        int status,
        String error,
        ErrorCode code,
        String message,
        List<ErrorField> errors,
        String path

) {
}
