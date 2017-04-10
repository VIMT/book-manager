package me.vimt.book.controller;

import me.vimt.book.config.ResponseCode;
import me.vimt.book.entity.BookEntity;
import me.vimt.book.entity.UserEntity;
import me.vimt.book.service.BookService;
import me.vimt.book.service.UserService;
import me.vimt.book.util.Result;
import me.vimt.book.util.exception.ExistException;
import me.vimt.book.util.exception.NotExistException;
import me.vimt.book.util.exception.StatusErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/6 22:10
 * Description:
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;


    @RequestMapping(value = "/books/", method = RequestMethod.POST)
    public Result addBook(BookEntity book) throws ExistException {
        bookService.addBook(book);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/books/", method = RequestMethod.PUT)
    public Result updateBook(BookEntity book, HttpServletRequest request) {
        bookService.updateBook(book);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.DELETE)
    public Result deleteBook(@PathVariable int bookId) throws NotExistException {
        bookService.deleteBook(bookId);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/books/{bookId}/readers", method = RequestMethod.GET)
    public Result<Set<UserEntity>> whoRead(@PathVariable int bookId) throws NotExistException {
        BookEntity book = bookService.getBook(bookId);
        return new Result<>(ResponseCode.SUCCESS, "", book.getUsers());
    }

    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    public Result<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return new Result<>(ResponseCode.SUCCESS, "", users);
    }
}
