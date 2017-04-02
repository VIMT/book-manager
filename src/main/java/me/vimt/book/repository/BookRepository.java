package me.vimt.book.repository;

import me.vimt.book.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/2 15:32
 * Description:
 */

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Page<BookEntity> findAllByName(String name, Pageable pageable);

}
