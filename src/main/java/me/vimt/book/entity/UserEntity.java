package me.vimt.book.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import me.vimt.book.util.MD5Util;
import me.vimt.book.util.RandomUtil;

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

    @ManyToMany(mappedBy = "users")
    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }
}
