package me.vimt.book.service;


import me.vimt.book.entity.UserEntity;
import me.vimt.book.util.exception.ExistException;
import me.vimt.book.util.exception.NotExistException;
import me.vimt.book.util.exception.ParamException;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2016/10/19 20:16
 * Description:
 */
public interface UserService {

    void createUser(UserEntity user) throws ExistException;

    void resetPassword(String email, String newPassword) throws NotExistException;

    UserEntity verify(String username, String password) throws ParamException, NotExistException;


}
