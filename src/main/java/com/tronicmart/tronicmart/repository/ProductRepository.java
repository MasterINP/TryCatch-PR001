package com.tronicmart.tronicmart.repository;

import com.tronicmart.tronicmart.model.Product;
import com.tronicmart.tronicmart.model.ProductSearchCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCriteria(ProductSearchCriteria criteria);
    void updateImage(Long id, byte[] image);
}
