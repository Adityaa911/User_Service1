package org.example.user_service1.Configurations;

import org.springframework.boot.web.server.Http2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfi {
    @Configuration
    public class SecurityConfiguration {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests((requests) -> {
                                try {
                                    requests
                                            .anyRequest().permitAll()
                                            .and().cors().disable()
                                            .csrf().disable();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );

            return http.build();
        }
    }
}
