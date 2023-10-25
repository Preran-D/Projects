package com.preran.BlogPost2.repositories;

import com.preran.BlogPost2.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long>  {
    Optional<User> findByName(String username);

}
