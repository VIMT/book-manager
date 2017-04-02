package me.vimt.book.util.exception;


public class NotExistException extends BaseException {

    public NotExistException(String message) {
        super(message);
    }

    public NotExistException(String message, Throwable cause) {
        super(message, cause);

    }
}
