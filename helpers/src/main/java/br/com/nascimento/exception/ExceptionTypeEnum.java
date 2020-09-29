package br.com.nascimento.exception;

import lombok.Getter;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
public enum ExceptionTypeEnum {

    ARGUMENT_NOT_VALID(MethodArgumentNotValidException.class),
    RESOURCE_NOT_FOUND(ResourceNotFoundException.class);

    ExceptionTypeEnum(Object exceptionType) {
        this.exceptionType = exceptionType;
    }

    private Object exceptionType;
}
