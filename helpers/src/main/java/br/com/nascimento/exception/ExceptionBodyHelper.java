package br.com.nascimento.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class ExceptionBodyHelper {

    private final Map<ExceptionTypeEnum, Function<Exception, Object>> exceptionBodyHandler = new HashMap<>();

    @PostConstruct
    private void init(){
        exceptionBodyHandler.put(ExceptionTypeEnum.ARGUMENT_NOT_VALID,
                obj -> BuildExceptionBodyhelper.getValidationErrorDetail(((MethodArgumentNotValidException) obj)));
        exceptionBodyHandler.put(ExceptionTypeEnum.RESOURCE_NOT_FOUND,
                obj -> BuildExceptionBodyhelper.getResourceNotFoundBody((ResourceNotFoundException) obj));
        exceptionBodyHandler.put(ExceptionTypeEnum.HTTP_MESSAGE_NOT_READABLE,
                obj -> BuildExceptionBodyhelper.getResourceNotFoundBody((HttpMessageNotReadableException) obj));
    }

    public Object getExceptionBody(ExceptionTypeEnum exceptionTypeEnum, Exception exception) {
        return exceptionBodyHandler.get(exceptionTypeEnum).apply(exception);
    };
}
