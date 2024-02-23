package com.tronicmart.tronicmart.controller;

import com.tronicmart.tronicmart.model.Product;
import com.tronicmart.tronicmart.model.ProductSearchCriteria;
import com.tronicmart.tronicmart.model.ProductView;
import com.tronicmart.tronicmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody ProductSearchCriteria criteria) {
        return productService.searchProducts(criteria);
    }

    @PostMapping("/{id}/image")
    public void uploadProductImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();
        productService.updateProductImage(id, imageBytes);
    }

    @GetMapping("/{id}")
    public ProductView getProductViewById(@PathVariable Long id) {
        return productService.getProductViewById(id);
    }

    @PostMapping("/{id}/addToCart")
    public void addToCart(@PathVariable Long id, @RequestParam Integer quantity) {
        productService.addToCart(id, quantity);
    }

    @PostMapping("/{id}/removeFromCart")
    public void removeFromCart(@PathVariable Long id, @RequestParam Integer quantity) {
        productService.removeFromCart(id, quantity);
    }
}
