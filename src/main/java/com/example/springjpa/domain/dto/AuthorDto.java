package com.example.springjpa.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public class AuthorDto {

    private Integer id;

    @NotEmpty(message = "Author needs a name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
