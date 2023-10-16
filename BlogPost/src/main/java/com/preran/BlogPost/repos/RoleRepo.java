package com.preran.BlogPost.repos;


import com.preran.BlogPost.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Roles, Long> {

    Roles findByUserName(String userName);
    boolean existsByRoleNameAndUserName(String roleName, String userName);


}
