package com.example.springjpa.service;

import com.example.springjpa.domain.Author;
import com.example.springjpa.domain.Book;
import com.example.springjpa.domain.Category;
import com.example.springjpa.domain.dto.AuthorDto;
import com.example.springjpa.domain.dto.CategoryDto;
import com.example.springjpa.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto create(AuthorDto authorDto) {
        Author authorToSave = new Author(authorDto.getName());
        return authorRepository.save(authorToSave).dto();
    }

    public List<AuthorDto> getAllAuthors() {
        return authorRepository
                .findAll()
                .stream()
                .map(Author::dto)
                .collect(Collectors.toList());
    }
}
