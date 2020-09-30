package br.com.nascimento.exception;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class ExceptionBodyHelper {

    private final Map<ExceptionTypeEnum, Function<Exception, Object>> exceptionBodyHandler = new HashMap<>();

    @PostConstruct
    private void init() {
        exceptionBodyHandler.put(ExceptionTypeEnum.RESOURCE_NOT_FOUND, BuildExceptionBodyhelper::getResourceNotFoundBody);
        exceptionBodyHandler.put(ExceptionTypeEnum.ARGUMENT_NOT_VALID, BuildExceptionBodyhelper::getValidationErrorDetail);
        exceptionBodyHandler.put(ExceptionTypeEnum.HTTP_MESSAGE_NOT_READABLE, BuildExceptionBodyhelper::getHttpMessageNotReadable);
        exceptionBodyHandler.put(ExceptionTypeEnum.INTERNAL_EXCEPTION, BuildExceptionBodyhelper::getInternalException);
    }

    public Object getExceptionBody(ExceptionTypeEnum exceptionTypeEnum, Exception exception) {
        return exceptionBodyHandler.get(exceptionTypeEnum).apply(exception);
    }

    ;
}
