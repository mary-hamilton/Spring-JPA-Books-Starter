package com.example.springjpa.domain;

import com.example.springjpa.domain.dto.MemberDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    public static final String USER_ROLE = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer memberId;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String roles;

    @ManyToMany
    @JoinTable(name = "lendings",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> borrowedBooks = new ArrayList<>();


    public Member(String firstName, String lastName, String username, String password, String roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Member() {
    }

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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public MemberDto dto() {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(this.memberId);
        memberDto.setFirstName(this.firstName);
        memberDto.setLastName(this.lastName);
        memberDto.setUsername(this.username);
        return memberDto;
    }

    public MemberDto dtoWithBorrowedBooks() {
        MemberDto memberDto = this.dto();
        if(this.borrowedBooks != null && !this.borrowedBooks.isEmpty()) {
            memberDto.setBorrowedBooks(this.borrowedBooks);
        }
        return memberDto;
    }
}
