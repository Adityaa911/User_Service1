package org.example.user_service1.Repository;

import org.example.user_service1.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

Optional<User> findByToken(String Token, String password);

    Optional<User> findByEmail(String email);

    User  save(User user);
}
