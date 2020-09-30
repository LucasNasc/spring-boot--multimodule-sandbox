package br.com.nascimento.exception;

import lombok.Getter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
public enum ExceptionTypeEnum {

    ARGUMENT_NOT_VALID(MethodArgumentNotValidException.class),
    RESOURCE_NOT_FOUND(ResourceNotFoundException.class),
    HTTP_MESSAGE_NOT_READABLE(HttpMessageNotReadableException.class);

    ExceptionTypeEnum(Object exceptionType) {
        this.exceptionType = exceptionType;
    }

    private Object exceptionType;
}
