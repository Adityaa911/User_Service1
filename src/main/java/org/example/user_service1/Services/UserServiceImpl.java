package org.example.user_service1.Services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.user_service1.Models.Tokens;
import org.example.user_service1.Models.User;
import org.example.user_service1.Repository.TokenRepository;
import org.example.user_service1.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;


    UserServiceImpl(TokenRepository tokenRepository,UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.tokenRepository=tokenRepository;
    }


    @Override
    public User SignUp(String name, String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        User user1=null;

        if(user.isPresent()){
            Login(email,password);
        }else {
            user1=new User();
            user1.setName(name);
            user1.setEmail(email);
            user1.setHashedPassword(bCryptPasswordEncoder.encode(password));
        }

        user1=userRepository.save(user1);

        return user1;
    }

    @Override
    public Tokens Login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = null;

        if(optionalUser.isEmpty()){
          // SignUp();
            }else{
            user= optionalUser.get();

            if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
                return null;
            }

            Tokens tokens = createToken(user);
            tokens=tokenRepository.save(tokens);
        }

         return  null;

    }

    private Tokens createToken(User user){

        Tokens tokens = new Tokens();
        tokens.setUser(user);
        tokens.setTokenvalue(RandomStringUtils.randomAlphabetic(128));

        LocalDate today = LocalDate.now();
        LocalDate thirtyDateLater = today.plus(30, ChronoUnit.DAYS);

        Date expiryDate = Date.from(thirtyDateLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        tokens.setExpiryAt(expiryDate);

        return tokens;
    }


    @Override
    public User ValidateToken(String token) {
        Optional<Tokens> tokensOptional =tokenRepository.findByvalueandisDeleteAndAndExpiryAtGreaterThan(
                token,
                false,
                new Date()
        );

        if(tokensOptional.isEmpty()){
             return null;
        }

        Tokens tokens = tokensOptional.get();

        return tokens.getUser();
    }

    @Override
    public void Logout(String token) {
     Optional<Tokens> optionalToken = tokenRepository.findByValueandDelete(token,false);

     if(optionalToken.isEmpty()){

     }
     Tokens token1 = optionalToken.get();
     token1.setDeleted(true);

     tokenRepository.save(token1);


    }

    @Override
    public String ForgetPassword(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()){
        }
        User user = optionalUser.get();
        Tokens tokens= createToken(user);

        user=userRepository.save(user);
        tokens=tokenRepository.save(tokens);

        return null ;
    }

    @Override
    public String ResetPassword(String Token,String password) {
        Optional<User> optionalUser = userRepository.findByToken(Token,password);

        if (!optionalUser.isPresent()){
            return "invlid token";
        }

        Date tokenCreation = optionalUser.get().getCreatedAt();

        User user = optionalUser.get();

        user.setName(user.getName());
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        userRepository.save(user);
        return "";
    }


}
