package me.vimt.book.service;


import me.vimt.book.entity.BookEntity;
import me.vimt.book.entity.UserEntity;
import me.vimt.book.util.exception.ExistException;
import me.vimt.book.util.exception.NotExistException;
import me.vimt.book.util.exception.ParamException;
import me.vimt.book.util.exception.StatusErrorException;

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

    UserEntity getUser(int id) throws NotExistException;

    void read(int userId, BookEntity book);

    void borrow(int userId, BookEntity book);

    void returnBook(int userId, BookEntity book);
}
