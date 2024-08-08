package org.example.user_service1.Repository;

import org.example.user_service1.Models.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Tokens,Long> {

     Tokens save(Tokens tokens);
     Optional <Tokens> findByvalueandisDeleteAndAndExpiryAtGreaterThan(String tokens, Boolean deleted, Date expriyAt);

     Optional<Tokens> findByValueandDelete(String tokens, Boolean t);
}
