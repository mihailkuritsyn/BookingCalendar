package domain;

/**
 */
public class BookingException extends Exception {

    public BookingException(String s) {
        super(s);
    }

    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingException(Throwable cause) {
        super(cause);
    }

    public BookingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BookingException() {
    }
}
