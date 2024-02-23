package com.tronicmart.tronicmart.repository;

import com.tronicmart.tronicmart.model.Cart;
import com.tronicmart.tronicmart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    public Cart findByUser(User user);
//   public Cart findByUser_id(Integer Id);
}
