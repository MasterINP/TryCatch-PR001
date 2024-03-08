package com.tronicmart.electronics.controller;

import com.tronicmart.electronics.Common.ApiResponse;
import com.tronicmart.electronics.dto.cart.AddToCartDto;
import com.tronicmart.electronics.model.User;
import com.tronicmart.electronics.services.AuthenticationService;
import com.tronicmart.electronics.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;


    //add to cart api
    @PostMapping("/")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token){
        //authenticate token
        authenticationService.authenticate(token);

        //fond the user
        User user = authenticationService.getUser(token);

        //Save items in cart

        cartService.addToCart(addToCartDto, user);
        ApiResponse apiResponse = new ApiResponse(true, "successfully added product to the cart,");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }


    //get all cart items for a user

    //delete cart items
}
