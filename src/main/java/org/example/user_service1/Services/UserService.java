package org.example.user_service1.Services;

import org.example.user_service1.Models.Tokens;
import org.example.user_service1.Models.User;

public interface UserService {

     User SignUp(String name, String email, String password);

    Tokens Login(String email, String password);
 
     User ValidateToken(String token);

     void Logout(String token);

     String ForgetPassword(String email);

    String ResetPassword(String Token,String password);
}
