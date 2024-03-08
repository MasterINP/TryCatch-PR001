package com.tronicmart.electronics.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInDto {
    private String email;
    private String password;

    public SignInDto(String password) {
        this.password = password;
    }
}
