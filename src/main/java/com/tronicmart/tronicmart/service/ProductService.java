package com.tronicmart.tronicmart.service;

import com.tronicmart.tronicmart.model.Product;
import com.tronicmart.tronicmart.model.ProductSearchCriteria;
import com.tronicmart.tronicmart.model.ProductView;
import com.tronicmart.tronicmart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
//advanced search
    public List<Product> searchProducts(ProductSearchCriteria criteria) {
        return productRepository.findByCriteria(criteria);
    }

    public void updateProductImage(Long id, byte[] image) {
        productRepository.updateImage(id, image);
    }

    public ProductView getProductViewById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductView productView = new ProductView();
            productView.setId(product.getId());
            productView.setName(product.getName());
            productView.setDescription(product.getDescription());
            productView.setPrice(product.getPrice());
            productView.setStockQuantity(product.getStockQuantity());
            productView.setStockQuantity(product.getStockQuantity());
            productView.setImage(product.getImage());
            return productView;
        }
        return null;
    }

    public void addToCart(Long id, Integer quantity) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setQuantity(product.getQuantity() + quantity);
            productRepository.save(product);
        }
    }

    public void removeFromCart(Long id, Integer quantity) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        }
    }
}
