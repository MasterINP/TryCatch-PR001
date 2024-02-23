package com.tronicmart.tronicmart.repository;

import com.tronicmart.tronicmart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String e);
}
