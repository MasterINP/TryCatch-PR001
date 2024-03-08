package com.tronicmart.electronics.dto.product;

import com.tronicmart.electronics.model.Products;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ProductDto {
    private int id;
    private String name;
    private String imgurl;
    private double price;
    private String description;
    private int categoryId;

    public ProductDto(Products products) {
        this.setId(products.getId());
        this.setName(products.getName());
        this.setImgurl(products.getImgurl());
        this.setDescription(products.getDescription());
        this.setPrice(products.getPrice());
        this.setCategoryId(products.getCategory().getId());
    }
}
