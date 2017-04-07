package me.vimt.book.config;

import me.vimt.book.util.Result;
import me.vimt.book.util.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2016/11/28 19:59
 * Description: 全局异常处理
 */
@ControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public Result myExceptionHandler(BaseException e) {

        if (e instanceof ExistException) {
            return new Result(ResponseCode.EXIST_ERROR, e.getMessage());
        } else if (e instanceof NotExistException) {
            return new Result(ResponseCode.EXIST_ERROR, e.getMessage());
        } else if (e instanceof ParamException) {
            return new Result(ResponseCode.PARAM_ERROR, e.getMessage());
        } else if (e instanceof PermissionException) {
            return new Result(ResponseCode.FORBIDDEN_ERROR, e.getMessage());
        } else if (e instanceof StatusErrorException) {
            return new Result(ResponseCode.STATE_ERROR, e.getMessage());
        } else {
            return new Result(ResponseCode.UNKOWN_ERROR, e.getMessage());
        }
    }

}