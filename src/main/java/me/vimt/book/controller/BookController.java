package me.vimt.book.controller;

import me.vimt.book.config.ResponseCode;
import me.vimt.book.entity.BookEntity;
import me.vimt.book.service.BookService;
import me.vimt.book.util.Result;
import me.vimt.book.util.exception.ExistException;
import me.vimt.book.util.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/2 14:21
 * Description:
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result<Page<BookEntity>> listBooks(@RequestParam(required = false, defaultValue = "") String query,
                                             Pageable pageable) {
        Page<BookEntity> books = bookService.searchBooks(query, pageable);
        return new Result<>(ResponseCode.SUCCESS, "", books);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addBook(@RequestBody BookEntity book) throws ExistException {
        bookService.addBook(book);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    public Result getBook(@PathVariable int bookId) throws NotExistException {
        bookService.getBook(bookId);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
    public Result updateBook(@PathVariable int bookId, @RequestBody BookEntity book) {
        book.setId(bookId);
        bookService.updateBook(book);
        return new Result(ResponseCode.SUCCESS, "");
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public Result deleteBook(@PathVariable int bookId) throws NotExistException {
        bookService.deleteBook(bookId);
        return new Result(ResponseCode.SUCCESS, "");
    }
}
