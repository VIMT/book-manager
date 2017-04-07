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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
