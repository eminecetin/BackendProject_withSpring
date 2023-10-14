package com.archis.starter.service.impl;

import com.archis.starter.dto.CategoryDto;
import com.archis.starter.dto.ProductDto;
import com.archis.starter.entity.Category;

import com.archis.starter.repository.CategoryRepository;
import com.archis.starter.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream().map(category->modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }



    @Override
    public CategoryDto getCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            throw new Exception("ıd'li user bulunamadı.!!.");
        }
        return modelMapper.map(category.get(),CategoryDto.class);     }


    @Override
    public String createCategory(CategoryDto category) {
        modelMapper.map(categoryRepository.save(modelMapper.map(category, Category.class)),
                CategoryDto.class);
        return "Category eklendi.. category id:"+ category.getId();
    }

    @Override
    public String updateCategory(Long id, CategoryDto categoryDto)  {
        Optional<Category> resultCategory=categoryRepository.findById(id);
        String response="Bu bilgiler ile herhangi category mevcut değil..";
        if(resultCategory.isPresent()){
            resultCategory.get().setCategoryName(categoryDto.getCategoryName());
            resultCategory.get().setDescription(categoryDto.getDescription());
            modelMapper.map(categoryRepository.save(resultCategory.get()),CategoryDto.class);
            return "Başarılı şekilde güncellendi";
        }
        return response;
    }

    @Override
    @Transactional
    public String delete(Long id) throws Exception {
        Optional<Category> category=categoryRepository.findById(id);
        if(category.isPresent()){
            throw new Exception("id'li kullanıcı bulunamadı!!");
        }
        categoryRepository.deleteById(id);
        return "Silindi!";
    }

}
