package me.vimt.book.util.exception;


public class StateAllowException extends BaseException {

    public StateAllowException(String msg) {
        super(msg);
    }

    public StateAllowException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
