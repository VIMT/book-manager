package me.vimt.book.controller;

import me.vimt.book.config.ResponseCode;
import me.vimt.book.entity.BookEntity;
import me.vimt.book.entity.UserEntity;
import me.vimt.book.service.BookService;
import me.vimt.book.service.UserService;
import me.vimt.book.util.Result;
import me.vimt.book.util.exception.ExistException;
import me.vimt.book.util.exception.NotExistException;
import me.vimt.book.util.exception.ParamException;
import me.vimt.book.util.exception.StatusErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2016/10/24 20:15
 * Description:
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam String username, @RequestParam String password) throws NotExistException, ParamException {
        UserEntity user;
        user = userService.verify(username, password);
        session.setAttribute("user", user.getId());
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        session.removeAttribute("user");
        return "<script>location.href=\"/login\"</script>";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result createUser(UserEntity user) throws ExistException {

        userService.createUser(user);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    public Result resetPassword(@RequestParam String email,
                                @RequestParam String newPassword) throws NotExistException {

        userService.resetPassword(email, newPassword);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result<UserEntity> getUserInfo() throws NotExistException {
        Integer user = (Integer) session.getAttribute("user");
        if(user == null) return new Result<>(ResponseCode.NOT_LOGIN, "您还未登录");
        UserEntity userEntity = userService.getUser(user);
        return new Result<>(ResponseCode.SUCCESS, "", userEntity);
    }

}
