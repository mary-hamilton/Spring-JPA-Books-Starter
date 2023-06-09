package com.example.springjpa.domain;

import com.example.springjpa.domain.dto.BookDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String synopsis;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Author author;

    @ManyToMany(mappedBy = "borrowedBooks")
    private List<Member> borrowedBy;

    public Book() {
    }

    public Book(String title, String synopsis, Category category, Author author) {
        this.title = title;
        this.synopsis = synopsis;
        this.category = category;
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public BookDto dto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(this.id);
        bookDto.setTitle(this.title);
        bookDto.setSynopsis(this.synopsis);
        bookDto.setCategoryId(this.category.getId());
        bookDto.setCategoryTitle(this.category.getTitle());
        bookDto.setCategoryDescription(this.category.getDescription());
        bookDto.setAuthorID(this.author.getId());
        bookDto.setAuthorName(this.author.getName());
        return bookDto;
    }

    public BookDto dtoWithMembers() {
        BookDto bookDto = this.dto();
        if(this.borrowedBy != null && !this.borrowedBy.isEmpty()) {
            bookDto.setBorrowedBy(this.borrowedBy
                    .stream()
                    .map(Member::dto)
                    .collect(Collectors.toList()));
        }
        return bookDto;
    }
}
