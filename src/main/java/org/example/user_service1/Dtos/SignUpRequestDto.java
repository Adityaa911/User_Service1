package org.example.user_service1.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
}
