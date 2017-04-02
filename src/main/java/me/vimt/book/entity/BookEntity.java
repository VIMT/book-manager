package me.vimt.book.entity;


import javax.persistence.*;
import java.util.Set;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/2 13:52
 * Description:
 */

@Entity
@Table(name = "book")
public class BookEntity {

    private int id;
    private String name;
    private String author;
    private String publisher;
    private CategoryEntity category;
    private int price;
    private int page;
    private String ISBN;
    private Set<UserEntity> users;


    //----getter setter constructor ---

    public BookEntity() {
    }

    public BookEntity(String name, String author, String publisher, CategoryEntity category, int price, int page, String ISBN) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.price = price;
        this.page = page;
        this.ISBN = ISBN;
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

    @Column(length = 50, nullable = false)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(length = 50, nullable = false)
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Column(length = 20, nullable = false)
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_book", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
