package com.tronicmart.electronics.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddToCartDto {
    private Integer id;
    private Integer productId;
    private Integer quantity;
}
