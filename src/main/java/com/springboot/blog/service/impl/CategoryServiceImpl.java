package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);

        Category saved = categoryRepository.save(category);

        return mapper.map(saved, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(long category_id) {

        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException(
                "Category", "id", category_id));

        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(long category_id, CategoryDto updated_category) {

        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException(
                "Category", "id", category_id));


        category.setName(updated_category.getName());
        category.setDescription(updated_category.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return mapper.map(updated_category, CategoryDto.class);
    }

    @Override
    public void deleteCategory(long category_id) {

        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException(
                "Category", "id", category_id));

        categoryRepository.delete(category);
    }
}
