package com.app.reservation.web.rest.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

import com.app.reservation.exception.ApplicationException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResource> handleApplicationException(ApplicationException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ErrorResource.builder().message(ex.getMessage()).build());
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ErrorResource handleException(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    private ErrorResource processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        List<FiledErrorResource> filedErrorResources = fieldErrors.stream()
                .map(fe -> new FiledErrorResource(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ErrorResource("Request contains errors", filedErrorResources);
    }

    @Value
    @Builder(toBuilder = true)
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorResource {

        String message;
        List<FiledErrorResource> filedErrors;

    }

    @Value
    @Builder(toBuilder = true)
    @AllArgsConstructor
    public static class FiledErrorResource {

        String field;
        String message;

    }

}
