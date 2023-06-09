package com.example.springjpa.domain.dto;

import com.example.springjpa.domain.Book;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class MemberDto {

    private Integer memberId;
//    @NotEmpty(message = "Member needs a first name")
    private String firstName;

//    @NotEmpty(message = "Member needs a last name")
    private String lastName;
    @NotEmpty(message = "Member needs a username")
    private String username;
    @NotEmpty(message = "Member needs a password")
    private String password;

    private List<Book> borrowedBooks = new ArrayList<>();

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
