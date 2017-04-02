package me.vimt.book.util.exception;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2016/11/28 20:37
 * Description:
 */
public class BaseException extends Exception {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
