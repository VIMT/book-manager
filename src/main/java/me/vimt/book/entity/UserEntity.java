package me.vimt.book.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import me.vimt.book.util.MD5Util;
import me.vimt.book.util.RandomUtil;
import java.time.LocalDateTime;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
public class UserEntity {

    private int id;
    private String username;
    private String email;
    private String password;
    private String salt;
    private Set<BookEntity> books;
    private boolean admin;
    private Set<BookEntity> borrowedBooks;


    public boolean verifyPassword(String password) {
        return this.password.equals(MD5Util.getMD5(password + this.salt));
    }

    public void generatePasswordAndSalt(String newPassword) {
        this.salt = RandomUtil.getStringRandom(8);
        this.password = MD5Util.getMD5(newPassword + salt);
    }

    //----getter setter constructor ---

    public UserEntity() {
        super();
    }

    public UserEntity(String name, String email, String password) {
        super();
        this.username = name;
        this.email = email;
        this.password = password;
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
    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    @Column(length = 50, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @Column(length = 40, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Column(length = 10, nullable = false)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_book", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }


    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
    public Set<BookEntity> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<BookEntity> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

}
