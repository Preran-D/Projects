package com.preran.BlogPost.services;

import com.preran.BlogPost.JsonModels.UserModel;
import com.preran.BlogPost.entities.Roles;
import com.preran.BlogPost.entities.Users;
import com.preran.BlogPost.repos.RoleRepo;
import com.preran.BlogPost.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    public UserModel addUser(UserModel userModel) {
        if (userModel.getName() != null && userModel.getPassword() != null) {

            if (userRepo.existsByUserName(userModel.getName())) {
                throw new RuntimeException("User exists");
            }

            Users newUsers = new Users();
            newUsers.setUserName(userModel.getName());
            newUsers.setUserPassword("{noop}" + userModel.getPassword());
            newUsers.setEmail(userModel.getEmail());
            newUsers.setEnable(true);
            newUsers = userRepo.save(newUsers);

            Roles newRole = new Roles();
            newRole.setUserName(userModel.getName());
            newRole.setRoleName("ROLE_" + userModel.getRole());
            roleRepo.save(newRole);

            userModel.setUMid(newUsers.getUserId());
            return userModel;
        } else {
            throw new RuntimeException("Enter valid data");
        }
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

}
