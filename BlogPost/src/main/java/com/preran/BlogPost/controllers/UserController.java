package com.preran.BlogPost.controllers;

import com.preran.BlogPost.JsonModels.UserModel;
import com.preran.BlogPost.entities.Roles;
import com.preran.BlogPost.exceptions.CustomException;
import com.preran.BlogPost.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {
        if (userModel != null) {
            return new ResponseEntity<>(userService.addUser(userModel), HttpStatus.OK);
        } else {
            throw new CustomException("Invalid data");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserModel userModel) {
        if (userModel != null) {
            return new ResponseEntity<>(userService.updatePassword(userModel), HttpStatus.OK);
        } else {
            throw new CustomException("Invalid data");
        }
    }

    @DeleteMapping("/remove/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable("userName") String userName) {
        if (userName != null) {
            return new ResponseEntity<>(userService.disableUser(userName), HttpStatus.OK);
        } else {
            throw new CustomException("Invalid data");
        }
    }

    @PostMapping("/add-roles")
    public ResponseEntity<?> addRolesToUser(@RequestBody Roles role) {
        String userName = role.getUserName();
        if (userName != null && role.getRoleName() != null) {
            var updatedUser = userService.updateUserRole(role);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            throw new CustomException("Invalid data");
        }
    }
}
