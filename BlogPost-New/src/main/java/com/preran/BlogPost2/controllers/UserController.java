package com.preran.BlogPost2.controllers;


import com.preran.BlogPost2.entites.User;
import com.preran.BlogPost2.exceptions.CustomResponse;
import com.preran.BlogPost2.models.UserDto;
import com.preran.BlogPost2.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto registeredUser = userService.registerNewUser(userDto);
        return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(
            @Valid @RequestBody UserDto userDto,
            Principal principal
    ) {
        String loggedInUsername = principal.getName();
        User loggedInUser = userService.findByName(loggedInUsername);
        long userId = loggedInUser.getId();
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") long uid, Principal principal) {
        String loggedInUsername = principal.getName();
        User loggedInUser = userService.findByName(loggedInUsername);
        if (loggedInUser.getId() != uid && !loggedInUser.getName().contains("admin")) {
            return new ResponseEntity<>(
                    "You are not allowed to access another user's details", HttpStatus.FORBIDDEN
            );
        }
        UserDto getUser = this.userService.getUserById(uid);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> updateUserRole(@PathVariable Long userId, @PathVariable int roleId) {
        UserDto updatedUser = userService.updateUserRole(userId, roleId);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}/addRole/{roleId}")
    public ResponseEntity<UserDto> addRoleToUser(@PathVariable Long userId, @PathVariable int roleId) {
        UserDto user = userService.addRoleToUser(userId, roleId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/delete/{userId}")
    public ResponseEntity<CustomResponse> deleteUser(@PathVariable("userId") long userId) {
        if (userId == 1) {
            return new ResponseEntity<>(
                    new CustomResponse("ADMIN cannot be deleted", false), HttpStatus.OK
            );
        }
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new CustomResponse(
                "User Deleted Successfully", true), HttpStatus.OK
        );
    }
}
