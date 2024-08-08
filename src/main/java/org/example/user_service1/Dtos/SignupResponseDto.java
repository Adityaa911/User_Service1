package org.example.user_service1.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.user_service1.Models.User;

@Getter
@Setter
public class SignupResponseDto {
    private User user;
    private ReponseStatus responseStatus;
}
