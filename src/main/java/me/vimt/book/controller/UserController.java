package me.vimt.book.controller;

import me.vimt.book.config.ResponseCode;
import me.vimt.book.entity.UserEntity;
import me.vimt.book.service.UserService;
import me.vimt.book.util.Result;
import me.vimt.book.util.exception.ExistException;
import me.vimt.book.util.exception.NotExistException;
import me.vimt.book.util.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam String username, @RequestParam String password) throws NotExistException, ParamException {
        UserEntity user;
        user = userService.verify(username, password);
        return new Result(ResponseCode.SUCCESS, "");
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result createUser(@RequestBody UserEntity user) throws ExistException {

        userService.createUser(user);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    public Result resetPassword(@RequestParam String email,
                                @RequestParam String newPassword) throws NotExistException {

        userService.resetPassword(email, newPassword);
        return new Result(ResponseCode.SUCCESS, "");
    }
}
