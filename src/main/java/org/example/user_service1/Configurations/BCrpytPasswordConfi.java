package org.example.user_service1.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCrpytPasswordConfi {

    @Bean
   public BCryptPasswordEncoder getPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
