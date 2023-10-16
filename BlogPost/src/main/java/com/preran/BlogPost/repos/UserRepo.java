package com.preran.BlogPost.repos;

import com.preran.BlogPost.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByUserName(String userName);
    boolean existsByUserName(String userName);
    Users findByUserId(long userId);
}
