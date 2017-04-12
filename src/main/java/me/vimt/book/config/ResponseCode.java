package me.vimt.book.config;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/2 14:35
 * Description:
 */
public enum ResponseCode {
    //0成功
    SUCCESS,
    //1请求参数异常
    PARAM_ERROR,
    //2禁止访问(权限不够)
    FORBIDDEN_ERROR,
    //3已经存在或者不存在
    EXIST_ERROR,
    //4服务器错误
    SERVER_ERROR,
    //5数据库错误
    DB_ERROR,
    //6未知错误
    UNKOWN_ERROR,
    //7状态异常
    STATE_ERROR,
    //8超出限制
    LIMITION_ERROR,
    //9未登录
    NOT_LOGIN,
    //10失败
    FAILURE


}
