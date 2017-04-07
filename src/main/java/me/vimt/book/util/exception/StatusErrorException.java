package me.vimt.book.util.exception;


public class StatusErrorException extends BaseException {

    public StatusErrorException(String msg) {
        super(msg);
    }

    public StatusErrorException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
