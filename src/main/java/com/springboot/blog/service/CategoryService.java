package com.springboot.blog.service;

import com.springboot.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(long category_id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(long category_id, CategoryDto updated_category);

    void deleteCategory(long category_id);

}
