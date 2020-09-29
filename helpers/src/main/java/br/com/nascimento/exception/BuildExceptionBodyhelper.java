package br.com.nascimento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

public abstract class BuildExceptionBodyhelper {

    public static ErrorDetail getResourceNotFoundBody(ResourceNotFoundException ex) {
        return ErrorDetail.builder()
                .Title("Resource Not Found")
                .httpStatusCode(ex.getHttpStatusCode())
                .code(ex.getCode())
                .description(ex.getDescription())
                .message(ex.getMessage())
                .build();
    }

    public static ErrorDetail getValidationErrorDetail(MethodArgumentNotValidException ex) {

        return  ErrorDetail.builder()
                .Title("Field Validation Error")
                .field(ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(FieldError::getField)
                        .collect(Collectors.joining(",")))
                .message(ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(",")))
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .build();
    }
}
