package com.tronicmart.electronics.controller;

import com.tronicmart.electronics.Common.ApiResponse;
import com.tronicmart.electronics.dto.product.ProductDto;
import com.tronicmart.electronics.model.Products;
import com.tronicmart.electronics.model.User;
import com.tronicmart.electronics.model.Wishlist;
import com.tronicmart.electronics.services.AuthenticationService;
import com.tronicmart.electronics.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;

    @Autowired
    AuthenticationService authenticationService;

    //save product as wishlist item
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Products products, @RequestParam("token") String token) {
        //authenticate token
        authenticationService.authenticate(token);

        //fond the user
        User user = authenticationService.getUser(token);

        //save the items in wishlist
        Wishlist wishlist = new Wishlist(user, products);
        wishlistService.createWishlist(wishlist);

        ApiResponse apiResponse = new ApiResponse(true, "successfully added,");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

    //get all wishlist items for a user
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {
        //authenticate token
        authenticationService.authenticate(token);

        //fond the user
        User user = authenticationService.getUser(token);
        List<ProductDto> productDtos = wishlistService.getWishListForUser(user);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}
