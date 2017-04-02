package me.vimt.book.util;

import me.vimt.book.config.ResponseCode;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2016/10/24 21:21
 * Description:
 */
public class Result<T> {
    private int code;
    private String message;
    private T body;

    public Result() {
        super();
    }

    public Result(ResponseCode code, String message, T body) {
        super();
        this.code = code.ordinal();
        this.message = message;
        this.body = body;
    }

    public Result(ResponseCode code, String message) {
        this.code = code.ordinal();
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "body=" + body +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
