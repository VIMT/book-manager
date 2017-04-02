
package me.vimt.book.util.exception;


public class PermissionException extends BaseException{

    public PermissionException(String msg) {
        super(msg);
    }

    public PermissionException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
