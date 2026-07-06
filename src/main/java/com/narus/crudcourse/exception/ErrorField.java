package com.narus.crudcourse.exception;

public record ErrorField(
        String field,
        String message
) {
}
