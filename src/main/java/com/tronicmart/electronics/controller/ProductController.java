package com.tronicmart.electronics.controller;

import com.tronicmart.electronics.Common.ApiResponse;
import com.tronicmart.electronics.dto.product.ProductDto;
import com.tronicmart.electronics.model.Category;
import com.tronicmart.electronics.repository.CategoryRepository;
import com.tronicmart.electronics.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    //create product
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exists"), HttpStatus.NOT_FOUND);
        }
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the Product"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> allProducts = productService.getAllProducts();
        return  new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @PostMapping("/update{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId,@RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exists"), HttpStatus.NOT_FOUND);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.CREATED);
    }
}


