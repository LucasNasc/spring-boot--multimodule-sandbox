package br.com.nascimento.exception;

import br.com.nascimento.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

public abstract class BuildExceptionBodyhelper {

    public static ErrorDetail getResourceNotFoundBody(Exception exception) {
        ResourceNotFoundException ex = (ResourceNotFoundException) exception;
        return ErrorDetail.builder()
                .title("Resource Not Found")
                .httpStatusCode(ex.getHttpStatusCode())
                .code(ex.getCode())
                .description(ex.getDescription())
                .developerMessage(ex.getMessage())
                .timestamp(DateUtils.formatOffsetDatetime(OffsetDateTime.now()))
                .build();
    }

    public static ErrorDetail getValidationErrorDetail(Exception exception) {
        MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
        return  ErrorDetail.builder()
                .title("Field Validation Error")
                .field(ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(FieldError::getField)
                        .collect(Collectors.joining(",")))
                .developerMessage(ex.getMessage())
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description(ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(",")))
                .timestamp(DateUtils.formatOffsetDatetime(OffsetDateTime.now()))
                .build();
    }

    public static ErrorDetail getHttpMessageNotReadable(Exception exception) {
        HttpMessageNotReadableException ex = (HttpMessageNotReadableException) exception;
        return ErrorDetail.builder()
                .title("Method Argument Not Valid")
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description(ex.getMessage())
                .developerMessage(ex.getCause().getMessage())
                .timestamp(DateUtils.formatOffsetDatetime(OffsetDateTime.now()))
                .build();
    }

    public static ErrorDetail getInternalException(Exception ex) {
        return ErrorDetail.builder()
                .title("Internal Error!")
                .description(ex.getMessage())
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .developerMessage(ex.getClass().getName())
                .timestamp(DateUtils.formatOffsetDatetime(OffsetDateTime.now()))
                .build();
    }
}
