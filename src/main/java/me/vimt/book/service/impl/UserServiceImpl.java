package me.vimt.book.service.impl;


import me.vimt.book.entity.BookEntity;
import me.vimt.book.entity.UserEntity;
import me.vimt.book.repository.BookRepository;
import me.vimt.book.repository.UserRepository;
import me.vimt.book.service.UserService;
import me.vimt.book.util.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2016/10/24 20:22
 * Description:
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public void createUser(UserEntity user) throws ExistException {
        UserEntity userByEmail = userRepository.getFirstByEmail(user.getEmail());
        if (userByEmail != null) {
            throw new ExistException("邮箱已存在");
        }
        UserEntity userByUsername = userRepository.getFirstByUsername(user.getUsername());
        if (userByUsername != null) {
            throw new ExistException("用户名已存在");
        }
        user.generatePasswordAndSalt(user.getPassword());

        userRepository.save(user);
    }

    @Override
    public void resetPassword(String email, String newPassword) throws NotExistException {
        UserEntity user = userRepository.getFirstByEmail(email);
        if (user == null)
            throw new NotExistException("用户不存在");
        user.generatePasswordAndSalt(newPassword);
        userRepository.save(user);
    }

    @Override
    public UserEntity verify(String username, String password) throws NotExistException, ParamException {
        UserEntity user = userRepository.getFirstByUsername(username);
        if (user == null)
            throw new NotExistException("该用户不存在");
        if (!user.verifyPassword(password))
            throw new ParamException("密码错误");
        return user;
    }

    @Override
    public UserEntity getUser(int id) throws NotExistException {
        UserEntity user = userRepository.findOne(id);
        if (user == null)
            throw new NotExistException("Not Found!");
        return user;
    }

    @Override
    public void read(int userId, BookEntity book) {
        UserEntity user = userRepository.findOne(userId);
        user.getBooks().add(book);
        userRepository.save(user);
    }

    @Override
    public void borrow(int userId, BookEntity book) {
        UserEntity user = userRepository.findOne(userId);
        book.setBorrower(user);
        read(userId, book);
        bookRepository.save(book);
    }

    @Override
    public void returnBook(int userId, BookEntity book) {
        book.setBorrower(null);
        bookRepository.save(book);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
