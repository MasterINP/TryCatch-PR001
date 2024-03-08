package com.tronicmart.electronics.repository;

import com.tronicmart.electronics.model.User;
import com.tronicmart.electronics.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {

    List<Wishlist> findAllByUserIdOrderByCreatedDateDesc(User user);
}
