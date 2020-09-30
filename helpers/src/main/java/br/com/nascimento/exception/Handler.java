package br.com.nascimento.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {

    @Autowired
    ExceptionBodyHelper exceptionBodyHelper;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleBusinessException(ResourceNotFoundException ex) {
        ErrorDetail exceptionBody = (ErrorDetail) exceptionBodyHelper.getExceptionBody(ExceptionTypeEnum.RESOURCE_NOT_FOUND, ex);
        return ResponseEntity.status(exceptionBody.getHttpStatusCode()).body(exceptionBody);
    }

    @Override
    @NonNull
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                          HttpHeaders headers,
                                                          HttpStatus status,
                                                          WebRequest request) {
        ErrorDetail exceptionBody = (ErrorDetail) exceptionBodyHelper.getExceptionBody(ExceptionTypeEnum.ARGUMENT_NOT_VALID, ex);
        return ResponseEntity.status(exceptionBody.getHttpStatusCode()).body(exceptionBody);
    }

    @Override
    @NonNull
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail exceptionBody = (ErrorDetail) exceptionBodyHelper.getExceptionBody(ExceptionTypeEnum.HTTP_MESSAGE_NOT_READABLE, ex);
        return ResponseEntity.status(exceptionBody.getHttpStatusCode()).body(exceptionBody);
    }

}
