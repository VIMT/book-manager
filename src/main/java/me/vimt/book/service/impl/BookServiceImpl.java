package me.vimt.book.service.impl;

import me.vimt.book.entity.BookEntity;
import me.vimt.book.entity.CategoryEntity;
import me.vimt.book.repository.BookRepository;
import me.vimt.book.repository.CategoryRepository;
import me.vimt.book.service.BookService;
import me.vimt.book.util.exception.ExistException;
import me.vimt.book.util.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/2 14:52
 * Description:
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void addBook(BookEntity book) throws ExistException {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(int bookId) throws NotExistException {

        bookRepository.delete(bookId);

    }

    @Override
    public void updateBook(BookEntity book) {

        bookRepository.save(book);

    }

    @Override
    public BookEntity getBook(int id) throws NotExistException {
        BookEntity one = bookRepository.findOne(id);
        if (one == null) throw new NotExistException("未找到id为" + id + "的书");
        return one;
    }

    @Override
    public Page<BookEntity> searchBooks(String query, Pageable pageable) {
        if (query.isEmpty()) {
            return bookRepository.findAll(pageable);
        } else {
            return bookRepository.findAllByName(query, pageable);
        }
    }
}
