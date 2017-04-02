package me.vimt.book.service;

import me.vimt.book.entity.BookEntity;
import me.vimt.book.util.exception.ExistException;
import me.vimt.book.util.exception.NotExistException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/2 14:29
 * Description:
 */

public interface BookService {

    void addBook(BookEntity book) throws ExistException;
    void deleteBook(int bookId) throws NotExistException;
    void updateBook(BookEntity book);
    BookEntity getBook(int id) throws NotExistException;
    Page<BookEntity> searchBooks(String query, Pageable pageable);
}
