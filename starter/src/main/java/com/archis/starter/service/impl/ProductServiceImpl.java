package com.archis.starter.service.impl;

import com.archis.starter.dto.CategoryDto;
import com.archis.starter.dto.ProductDto;
import com.archis.starter.entity.Category;
import com.archis.starter.entity.Product;
import com.archis.starter.repository.CategoryRepository;
import com.archis.starter.repository.ProductRepository;
import com.archis.starter.service.CategoryService;
import com.archis.starter.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream().map(product->modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductyById(Long id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new Exception("ıd'li user bulunamadı..");
        }
        return modelMapper.map(product.get(),ProductDto.class);    }


    @Override
    public String createProduct(ProductDto productDto, Long categoryId ) throws Exception {
        CategoryDto category=categoryService.getCategoryById(categoryId);
        productDto.setCategory(category);
        modelMapper.map(productRepository.save(modelMapper.map(productDto,Product.class)),
                ProductDto.class);
        return "Başarılı şekilde oluşturuldu";
    }

    @Override
    public String updateProduct(Long id, ProductDto productDto) throws Exception {
        Optional<Product> resultProduct=productRepository.findById(id);
        if(!resultProduct.isPresent()){
            throw new Exception("id'li user bulunamadı..");
        }
        resultProduct.get().setProductName(productDto.getProductName());
        resultProduct.get().setPhotoUrl(productDto.getPhotoUrl());

        modelMapper.map(productRepository.save(resultProduct.get()),Product.class);
        return "Başarılı şekilde güncellendi";
    }
    @Override
    public String deleteProduct(Long id) throws Exception {
        Optional<Product> user=productRepository.findById(id);
        if(user.isPresent()){
            throw new Exception("id'li kullanıcı bulunamadı!!");
        }
        productRepository.deleteById(id);
        return "Silindi!";    }
}
