package by.realovka.test.service.exception;

public class UserAccountIsInactiveException extends RuntimeException {

    public UserAccountIsInactiveException() {
        super();
    }

    public UserAccountIsInactiveException(String message) {
        super(message);
    }

    public UserAccountIsInactiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAccountIsInactiveException(Throwable cause) {
        super(cause);
    }

    protected UserAccountIsInactiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
