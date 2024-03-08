package com.tronicmart.electronics.services;

import com.tronicmart.electronics.dto.product.ProductDto;
import com.tronicmart.electronics.model.Category;
import com.tronicmart.electronics.model.Products;
import com.tronicmart.electronics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public static ProductDto getDtoFromProduct(Products products) {
        ProductDto productDto = new ProductDto(products);
        return productDto;
    }

    public void createProduct(ProductDto productDto, Category category) {
        Products products = new Products();
        products.setName(productDto.getName());
        products.setDescription(productDto.getDescription());
        products.setPrice(productDto.getPrice());
        products.setImgurl(productDto.getImgurl());
        products.setCategory(category);
        productRepository.save(products);
    }

    public ProductDto getProductDto(Products products) {
        ProductDto productsDto = new ProductDto();
        productsDto.setId(products.getId());
        productsDto.setName(products.getName());
        productsDto.setDescription(products.getDescription());
        productsDto.setPrice(products.getPrice());
        productsDto.setImgurl(products.getImgurl());
        productsDto.setCategoryId(products.getCategory().getId());
        return productsDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Products> allProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Products products : allProducts) {
            productDtos.add(getProductDto(products));
        }
        return productDtos;
    }


    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        Optional<Products> optionalProducts = productRepository.findById(productId);
        //if product does not exist
        if (!optionalProducts.isPresent()) {
            throw new Exception("product does not exist");
        }
        Products products = optionalProducts.get();
        products.setName(productDto.getName());
        products.setDescription(productDto.getDescription());
        products.setPrice(productDto.getPrice());
        products.setImgurl(productDto.getImgurl());
        productRepository.save(products);
    }
}
