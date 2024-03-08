package com.tronicmart.electronics.services;

import com.tronicmart.electronics.dto.product.ProductDto;
import com.tronicmart.electronics.model.User;
import com.tronicmart.electronics.model.Wishlist;
import com.tronicmart.electronics.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {
    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ProductService productService;

    public void createWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    public List<ProductDto> getWishListForUser(User user) {
        List<Wishlist> woshlists = wishlistRepository.findAllByUserIdOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Wishlist wishlist : woshlists){
            productDtos.add(productService.getProductDto(wishlist.getProducts()));
        }

        return productDtos;
    }
}
