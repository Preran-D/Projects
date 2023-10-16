package com.preran.BlogPost.services;

import com.preran.BlogPost.JsonModels.UserModel;
import com.preran.BlogPost.entities.Roles;
import com.preran.BlogPost.entities.Users;
import com.preran.BlogPost.exceptions.CustomException;
import com.preran.BlogPost.repos.RoleRepo;
import com.preran.BlogPost.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    public UserModel addUser(UserModel userModel) {
        if (!userModel.getName().isEmpty() && !userModel.getPassword().isEmpty()) {
            if (userRepo.existsByUserName(userModel.getName()) && !userModel.isEnable()) {
                userModel.setEnable(true);
            }
            userModel = setUserDetails(userModel);
            return userModel;
        } else {
            throw new CustomException("Enter valid data");
        }
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public UserModel updatePassword(UserModel userModel) {
        Users existingUser = userRepo.findByUserName(userModel.getName());
        if (existingUser != null) {
            if (!userRepo.existsByUserName(existingUser.getUserName())) {
                throw new ClassCastException("User doesn't exists");
            }
            existingUser.setUserPassword("{noop}" + userModel.getPassword());
            userRepo.save(existingUser);
            return userModel;
        } else {
            throw new CustomException("Enter valid data");
        }
    }

    public UserModel setUserDetails(UserModel userModel) {
        Users assignUser = new Users();
        Roles role;
        if (userRepo.existsByUserName(userModel.getName())) {
            assignUser.setUserName(userModel.getName());
            assignUser.setUserPassword("{noop}" + userModel.getPassword());
            assignUser = userRepo.save(assignUser);

            role = roleRepo.findByUserName(userModel.getName());
            userModel.setRole(role.getRoleName());
            userModel.setUMid(assignUser.getUserId());
            return userModel;
        }
        assignUser.setUserName(userModel.getName());
        assignUser.setUserPassword("{noop}" + userModel.getPassword());
        assignUser.setEmail(userModel.getEmail());
        assignUser.setEnable(userModel.isEnable());
        assignUser = userRepo.save(assignUser);

        Roles assignRole = new Roles();
        assignRole.setUserName(userModel.getName());
        assignRole.setRoleName("ROLE_" + userModel.getRole());
        role = roleRepo.save(assignRole);

        userModel.setRole(role.getRoleName());
        userModel.setUMid(assignUser.getUserId());
        return userModel;
    }

    @Transactional
    public UserModel disableUser(String userName) {
        UserModel userModel = new UserModel();
        Users existingUser = userRepo.findByUserName(userName);
        if (existingUser != null) {
            existingUser.setEnable(false);
            existingUser = userRepo.save(existingUser);

            Roles existingRole = roleRepo.findByUserName(existingUser.getUserName());
            roleRepo.delete(existingRole);

            userModel.setUMid(existingUser.getUserId());
            userModel.setRole(existingRole.getRoleName());
            userModel.setEmail(existingUser.getEmail());
            userModel.setPassword(existingUser.getUserPassword());
            userModel.setEnable(existingUser.isEnable());
            return userModel;
        } else {
            throw new CustomException("User doesn't exists");
        }
    }

    @Transactional
    public UserModel updateUserRole(Roles role) {
        String userName = role.getUserName();
        Users existingUser = userRepo.findByUserName(userName);
        if (existingUser != null && existingUser.isEnable()) {
            UserModel updatedUserRoles = new UserModel();
            updatedUserRoles.setRole(applyRole(role).getRoleName());
            updatedUserRoles.setUMid(existingUser.getUserId());
            updatedUserRoles.setName(existingUser.getUserName());
            updatedUserRoles.setPassword(existingUser.getUserPassword());
            updatedUserRoles.setEmail(existingUser.getEmail());
            updatedUserRoles.setEnable(existingUser.isEnable());
            return updatedUserRoles;
        } else {
            throw new CustomException("User Not Found");
        }
    }

    @Transactional
    public Roles applyRole(Roles role) {
        String roleName = role.getRoleName();
        String userName = role.getUserName();
        if (!roleRepo.existsByRoleNameAndUserName(roleName, userName)) {
            role.setRoleName("ROLE_" + roleName);
            return roleRepo.save(role);
        } else {
            throw new CustomException("User Not Found");
        }
    }


}
