package com.tronicmart.electronics.controller;

import com.tronicmart.electronics.dto.user.SignUpResponseDto;
import com.tronicmart.electronics.dto.user.SignInDto;
import com.tronicmart.electronics.dto.user.SignInResponseDto;
import com.tronicmart.electronics.dto.user.SignUpDto;
import com.tronicmart.electronics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    //two API s

    //sign in
    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

    //sign up
    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpDto signUpDto) {
        return userService.signUp(signUpDto);
    }
}
