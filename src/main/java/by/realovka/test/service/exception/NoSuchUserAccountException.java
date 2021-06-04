package by.realovka.test.service.exception;

public class NoSuchUserAccountException extends RuntimeException {
    public NoSuchUserAccountException() {
        super();
    }

    public NoSuchUserAccountException(String message) {
        super(message);
    }

    public NoSuchUserAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUserAccountException(Throwable cause) {
        super(cause);
    }

    protected NoSuchUserAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
