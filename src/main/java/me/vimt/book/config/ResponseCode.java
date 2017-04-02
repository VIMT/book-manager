package me.vimt.book.config;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/2 14:35
 * Description:
 */
public enum ResponseCode {
    //成功
    SUCCESS,
    //请求参数异常
    PARAM_ERROR,
    // 禁止访问(权限不够)
    FORBIDDEN_ERROR,
    // 已经存在或者不存在
    EXIST_ERROR,
    // 服务器错误
    SERVER_ERROR,
    // 数据库错误
    DB_ERROR,
    // 未知错误
    UNKOWN_ERROR,
    // 状态异常
    STATE_ERROR,
    // 超出限制
    LIMITION_ERROR,
    // 未登录
    NOT_LOGIN,
    //失败
    FAILURE


}
