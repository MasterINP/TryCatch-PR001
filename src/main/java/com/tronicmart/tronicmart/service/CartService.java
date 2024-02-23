package com.tronicmart.tronicmart.service;

import com.tronicmart.tronicmart.payload.CartDto;
import com.tronicmart.tronicmart.payload.CartHelp;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    //Create
    CartDto CreateCart(CartHelp cartHelp);

    //add Product To Cart
    CartDto addProductToCart(CartHelp cartHelp);

    //Get
    CartDto GetCart(String userEmail);

    //delete product

    void RemoveById(Integer ProductId,String userEmail);
}
