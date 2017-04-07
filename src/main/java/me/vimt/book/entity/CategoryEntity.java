package me.vimt.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/2 13:54
 * Description:
 */

@Entity
@Table(name = "category")
public class CategoryEntity {

    private int id;
    private String name;
    private Set<BookEntity> books;


    //----getter setter constructor ---

    public CategoryEntity() {
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }
}
