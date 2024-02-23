package com.tronicmart.tronicmart.service.impl;

import com.tronicmart.tronicmart.payload.CartDetailDto;
import com.tronicmart.tronicmart.payload.CartHelp;
import com.tronicmart.tronicmart.repository.CartRepository;
import com.tronicmart.tronicmart.repository.UserRepository;
import com.tronicmart.tronicmart.service.CartDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

public class CartDetailsServiceImpl implements CartDetailsService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserRepository userRepo;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CartRepository cartRepo;




    @Override
    public CartDetailDto addProduct(CartHelp cartHelp) {
        int productId=cartHelp.getProductId();
        int quantity= cartHelp.getQuantity();
        String userEmail= cartHelp.getUserEmail();

        //get user


        return null;
    }
}
