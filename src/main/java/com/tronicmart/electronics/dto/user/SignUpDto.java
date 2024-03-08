package com.tronicmart.electronics.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDto {
    private int id;
    private String FirstName;
    private String LastName;
    private String email;
    private String password;
}
