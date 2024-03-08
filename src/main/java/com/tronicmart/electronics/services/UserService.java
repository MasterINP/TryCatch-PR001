package com.tronicmart.electronics.services;

import com.tronicmart.electronics.dto.user.SignUpResponseDto;
import com.tronicmart.electronics.dto.user.SignInDto;
import com.tronicmart.electronics.dto.user.SignInResponseDto;
import com.tronicmart.electronics.dto.user.SignUpDto;
import com.tronicmart.electronics.exceptions.AuthenticationFailException;
import com.tronicmart.electronics.exceptions.CustomException;
import com.tronicmart.electronics.model.AuthenticationToken;
import com.tronicmart.electronics.model.User;
import com.tronicmart.electronics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public SignUpResponseDto signUp(SignUpDto signUpDto) {
        //check if user alredy signed up
        if (Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))) {
            //user already exists
            throw new CustomException("User already Exists");
        }
        //hash the password
        String encryptedPassword = signUpDto.getPassword();

        try {
            encryptedPassword = hashPassword(signUpDto.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }

        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(encryptedPassword);
        userRepository.save(user);


        //save the user

        //cerate the token
        AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        SignUpResponseDto responseDto = new SignUpResponseDto("succes", "dummy response");
        return responseDto;

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        //find user by email
        User user = userRepository.findByEmail(signInDto.getEmail());

        if (Objects.isNull(user)){
            throw new AuthenticationFailException("Please enter valid email");
        }

        //hash the password
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException("password is incoreect");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //if password matches
        AuthenticationToken token = authenticationService.getToken(user);

        //retrieve token
        if (Objects.isNull(token)){
            throw new CustomException("token does not exist");
        }
        return new SignInResponseDto("success",token.getToken());
    }
}
