package com.archis.starter.service;

import com.archis.starter.dto.ProductDto;


import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductyById(Long id) throws Exception;
    String createProduct(ProductDto productDto, Long id) throws Exception;
    String updateProduct(Long id, ProductDto productDto) throws Exception;
    String deleteProduct(Long id) throws Exception;
}
