package com.example.springjpa.resources;

import com.example.springjpa.domain.dto.AuthorDto;
import com.example.springjpa.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/authors")
    public class AuthorResource {

        private final AuthorService authorService;

        public AuthorResource(AuthorService authorService) {
            this.authorService = authorService;
        }

        @GetMapping
        public ResponseEntity<List<AuthorDto>> getAllAuthors() {
            List<AuthorDto> authors = authorService.getAllAuthors();
            return new ResponseEntity<>(authors, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
            AuthorDto createdAuthor = authorService.create(authorDto);
            return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
        }

    }
