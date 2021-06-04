package by.realovka.test.advice;

import by.realovka.test.service.exception.SuchUsernameExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ResponseEntityAdviceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = SuchUsernameExistsException.class)
    protected ResponseEntity<Object> handleSuchUsernameExistsException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Such username exists";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}