package com.example.springjpa.service;

import com.example.springjpa.domain.Author;
import com.example.springjpa.domain.Book;
import com.example.springjpa.domain.Category;
import com.example.springjpa.domain.dto.BookDto;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.BookRepository;
import com.example.springjpa.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    private final CategoryRepository categoryRepository;

    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    public BookDto create(BookDto bookDto) {
        Category foundCategory = categoryRepository
                .findById(bookDto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find category"));
        Author foundAuthor = authorRepository
                .findById(bookDto.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find author"));
        Book bookToSave = new Book(bookDto.getTitle(), bookDto.getSynopsis(), foundCategory, foundAuthor);
        return bookRepository.save(bookToSave).dto();
    }

    public List<BookDto> getAllBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(Book::dtoWithMembers)
                .collect(Collectors.toList());
    }
}
