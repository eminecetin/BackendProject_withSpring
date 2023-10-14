package com.archis.starter.controller;

import com.archis.starter.dto.CategoryDto;
import com.archis.starter.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAll(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) throws Exception{
        return categoryService.getCategoryById(id);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto){
        return   ResponseEntity.ok(categoryService.createCategory(categoryDto));

    }

    @PutMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(id,categoryDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(categoryService.delete(id));
    }











}
