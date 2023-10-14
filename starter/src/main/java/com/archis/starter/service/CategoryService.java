package com.archis.starter.service;

import com.archis.starter.dto.CategoryDto;
import com.archis.starter.dto.ProductDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id) throws Exception;
    String createCategory(CategoryDto category);
    String updateCategory(Long id, CategoryDto categoryDto);
    String delete(Long id) throws Exception;
}
