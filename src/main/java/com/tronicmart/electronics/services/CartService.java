package com.tronicmart.electronics.services;

import com.tronicmart.electronics.dto.cart.AddToCartDto;
import com.tronicmart.electronics.model.User;
import com.tronicmart.electronics.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    public void addToCart(AddToCartDto addToCartDto, User user) {
        //validate the product id

        //save the cart
    }
}
