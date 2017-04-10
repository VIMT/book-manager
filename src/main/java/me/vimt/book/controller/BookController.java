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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;


/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/2 14:21
 * Description:
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result<List<BookEntity>> listBooks(@RequestParam(required = false, defaultValue = "") String query,
                                              Pageable pageable) {
        Page<BookEntity> books = bookService.searchBooks(query, pageable);
        return new Result<>(ResponseCode.SUCCESS, "", books.getContent());
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public Result<Set<BookEntity>> getBooks(@RequestParam(required = false) boolean read,
                                            @RequestParam(required = false) boolean borrow) throws NotExistException {
        Integer user_id = (Integer) session.getAttribute("user");
        if (read) {
            UserEntity user = userService.getUser(user_id);
            return new Result<>(ResponseCode.SUCCESS, "", user.getBooks());
        } else if (borrow) {
            UserEntity user = userService.getUser(user_id);
            return new Result<>(ResponseCode.SUCCESS, "", user.getBorrowedBooks());
        }
        return new Result<>(ResponseCode.PARAM_ERROR, "输入参数有误");
    }


    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    public Result<BookEntity> getBook(@PathVariable int bookId) throws NotExistException {
        BookEntity book = bookService.getBook(bookId);
        return new Result<>(ResponseCode.SUCCESS, "", book);
    }

    @RequestMapping(value = "/{bookId}/read", method = RequestMethod.POST)
    public Result readABook(@PathVariable int bookId) throws NotExistException, StatusErrorException {
        Integer user_id = (Integer) session.getAttribute("user");
        BookEntity book = bookService.getBook(bookId);
        userService.read(user_id, book);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/{bookId}/borrow", method = RequestMethod.POST)
    public Result borrowABook(@PathVariable int bookId) throws NotExistException, StatusErrorException {
        Integer user_id = (Integer) session.getAttribute("user");
        BookEntity book = bookService.getBook(bookId);
        if (book.getBorrower() != null) {
            return new Result(ResponseCode.EXIST_ERROR, book.getBorrower().getUsername() + "已借此书");
        }
        userService.borrow(user_id, book);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/{bookId}/return", method = RequestMethod.POST)
    public Result returnABook(@PathVariable int bookId) throws NotExistException, StatusErrorException {
        Integer user_id = (Integer) session.getAttribute("user");
        BookEntity book = bookService.getBook(bookId);
        userService.returnBook(user_id, book);
        return new Result(ResponseCode.SUCCESS, "");
    }

}
