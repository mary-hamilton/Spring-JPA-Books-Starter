package com.example.springjpa.domain.dto;

import jakarta.validation.constraints.NotNull;

public class LendingDto {
    @NotNull(message = "Needs Book ID")
    private Integer bookId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
