package me.vimt.book.util.exception;

public class ExistException extends BaseException {

    public ExistException(String msg) {
        super(msg);
    }

    public ExistException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
