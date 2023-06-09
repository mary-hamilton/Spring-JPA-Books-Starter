package com.example.springjpa.service;

import com.example.springjpa.domain.Book;
import com.example.springjpa.domain.Category;
import com.example.springjpa.domain.dto.BookDto;
import com.example.springjpa.domain.dto.CategoryDto;
import com.example.springjpa.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto create(CategoryDto categoryDto) {
        Category categoryToSave = new Category(categoryDto.getTitle(), categoryDto.getDescription());
        return categoryRepository.save(categoryToSave).dto();
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(Category::dto)
                .collect(Collectors.toList());
    }
}
