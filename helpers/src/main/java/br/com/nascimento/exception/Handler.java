package br.com.nascimento.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class Handler {

    @Autowired
    ExceptionBodyHelper exceptionBodyHelper;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleBusinessException(ResourceNotFoundException ex) {
        ErrorDetail exceptionBody = (ErrorDetail) exceptionBodyHelper.getExceptionBody(ExceptionTypeEnum.RESOURCE_NOT_FOUND, ex);
        return ResponseEntity.status(exceptionBody.getHttpStatusCode()).body(exceptionBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ErrorDetail exceptionBody = (ErrorDetail) exceptionBodyHelper.getExceptionBody(ExceptionTypeEnum.ARGUMENT_NOT_VALID, ex);
        return ResponseEntity.status(exceptionBody.getHttpStatusCode()).body(exceptionBody);
    }

}
