package by.realovka.test.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SuchUsernameExistsException extends RuntimeException {
    public SuchUsernameExistsException() {
        super();
    }

    public SuchUsernameExistsException(String message) {
        super(message);
    }

    public SuchUsernameExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchUsernameExistsException(Throwable cause) {
        super(cause);
    }

    protected SuchUsernameExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
