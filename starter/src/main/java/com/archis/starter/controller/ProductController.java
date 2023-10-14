package com.archis.starter.controller;

import com.archis.starter.dto.CategoryDto;
import com.archis.starter.dto.ProductDto;
import com.archis.starter.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<ProductDto> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) throws Exception{
        return productService.getProductyById(id);
    }
    @PostMapping("/create/{id}")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto,@PathVariable long id) throws Exception {
        return ResponseEntity.ok(productService.createProduct(productDto, id));
    }


    @PutMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @RequestBody ProductDto productDto) throws Exception {
        return productService.updateProduct(id,productDto);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("userId") Long id) throws Exception {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }













}
