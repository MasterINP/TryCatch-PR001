package com.tronicmart.tronicmart.repository;

import com.tronicmart.tronicmart.model.Cart;
import com.tronicmart.tronicmart.model.CartDetalis;
import com.tronicmart.tronicmart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailsRepository extends JpaRepository<CartDetalis,Integer> {
    public void deleteByProductsAndCart(Product product, Cart cart);
    public CartDetalis findByProductsAndCart(Product product, Cart cart);
}
