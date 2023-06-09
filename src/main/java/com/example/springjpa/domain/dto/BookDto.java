package com.example.springjpa.domain.dto;

import com.example.springjpa.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BookDto {

    private Integer id;

    @NotEmpty(message = "Book needs a title")
    private String title;

    @NotEmpty(message = "Book needs a synopsis")
    private String synopsis;

    @NotNull(message = "Book needs a category ID")
    private Integer categoryId;

    private String categoryTitle;

    private String categoryDescription;

    @NotNull(message = "Book needs an author ID")
    private Integer authorId;

    private String authorName;

    private List<MemberDto> borrowedBy;

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public List<MemberDto> getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(List<MemberDto> borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorID(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
