package com.preran.BlogPost2.services;




import com.preran.BlogPost2.entites.User;
import com.preran.BlogPost2.exceptions.CustomResponse;
import com.preran.BlogPost2.models.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto userDto);
    UserDto updateUser(UserDto user, Long userId);
    UserDto getUserById(Long userDto);
    List<UserDto>getAllUsers();
    void deleteUser(long Id);

    UserDto updateUserRole(Long userId, int roleId);

    UserDto addRoleToUser(Long userId, int roleId);


    User findById(long uid);

    User findByName(String loggedInUsername);
}
