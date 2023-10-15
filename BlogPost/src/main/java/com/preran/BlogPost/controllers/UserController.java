package com.preran.BlogPost.controllers;

import com.preran.BlogPost.JsonModels.UserModel;
import com.preran.BlogPost.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {
        if (userModel != null) {
            return new ResponseEntity<>(userService.addUser(userModel), HttpStatus.OK);
        } else {
            throw new RuntimeException("Invalid data");
        }
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

}
