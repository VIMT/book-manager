
package me.vimt.book.util.exception;


public class ParamException extends BaseException{

    public ParamException(String msg) {
        super(msg);
    }

    public ParamException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
