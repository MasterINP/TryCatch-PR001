package com.tronicmart.electronics.services;

import com.tronicmart.electronics.exceptions.AuthenticationFailException;
import com.tronicmart.electronics.model.AuthenticationToken;
import com.tronicmart.electronics.model.User;
import com.tronicmart.electronics.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    public User getUser(String token){
        AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if (Objects.isNull(authenticationToken)){
            return null;
        }
        //token is not null
        return authenticationToken.getUser();
    }

    public void authenticate(String token)throws AuthenticationFailException{
        if (Objects.isNull(token)){
            //throws an exception
            throw new AuthenticationFailException("token is not exists");
        }
        if (Objects.isNull(getUser(token))){
            throw new AuthenticationFailException("token is not valid");
        }
    }
}
