package com.example.springjpa.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDto {

    private Integer id;

    @NotEmpty(message = "Category needs a title")
    private String title;

    @NotEmpty(message = "Category needs a description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
