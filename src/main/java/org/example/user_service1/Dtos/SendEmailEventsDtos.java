package org.example.user_service1.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailEventsDtos {
    private String to;
    private String from;
    private String subject;
    private String Body;
}
