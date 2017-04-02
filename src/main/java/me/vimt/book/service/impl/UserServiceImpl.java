package me.vimt.book.service.impl;


import me.vimt.book.entity.UserEntity;
import me.vimt.book.repository.UserRepository;
import me.vimt.book.service.UserService;
import me.vimt.book.util.exception.ExistException;
import me.vimt.book.util.exception.NotExistException;
import me.vimt.book.util.exception.ParamException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    HttpServletRequest request;

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
    public UserEntity verify(String email, String password) throws NotExistException, ParamException {
        UserEntity user = userRepository.getFirstByEmail(email);
        if (user == null)
            throw new NotExistException("Not Found!");
        if (!user.verifyPassword(password))
            throw new ParamException("密码错误");
        return user;
    }

}
