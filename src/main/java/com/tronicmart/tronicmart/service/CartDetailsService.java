package com.tronicmart.tronicmart.service;

import com.tronicmart.tronicmart.payload.CartDetailDto;
import com.tronicmart.tronicmart.payload.CartHelp;

public interface CartDetailsService {
    //add product
    public CartDetailDto addProduct(CartHelp cartHelp);
}
