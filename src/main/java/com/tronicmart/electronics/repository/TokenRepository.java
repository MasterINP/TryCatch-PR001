package com.tronicmart.electronics.repository;

import com.tronicmart.electronics.model.AuthenticationToken;
import com.tronicmart.electronics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
