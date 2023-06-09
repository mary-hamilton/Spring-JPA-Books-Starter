package com.example.springjpa.domain;

import com.example.springjpa.domain.dto.AuthorDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

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

    public AuthorDto dto() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(this.id);
        authorDto.setName(this.name);
        return authorDto;
    }
}


