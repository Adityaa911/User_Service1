package org.example.user_service1.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.user_service1.Models.Tokens;

@Getter
@Setter
public class LogoutRequestDto {
    private String tokens;
}
