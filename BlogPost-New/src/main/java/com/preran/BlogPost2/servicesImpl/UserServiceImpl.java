package com.preran.BlogPost2.servicesImpl;

import com.preran.BlogPost2.entites.Role;
import com.preran.BlogPost2.entites.User;
import com.preran.BlogPost2.exceptions.ResourceFoundException;
import com.preran.BlogPost2.exceptions.ResourceNotFoundException;
import com.preran.BlogPost2.models.UserDto;
import com.preran.BlogPost2.repositories.RoleRepo;
import com.preran.BlogPost2.repositories.UserRepo;
import com.preran.BlogPost2.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto registerDto) {
        if (userRepo.findByName(registerDto.getName()).isPresent()) {
            throw new ResourceFoundException("User", registerDto.getName());
        }
        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role role = roleRepo.findById(101).orElseThrow(() ->
                new ResourceNotFoundException("Role", "Id", 101));
        user.getRoles().add(role);
        User newUser = userRepo.save(user);
        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        if (userRepo.findByName(userDto.getName()).isPresent()) {
            throw new ResourceFoundException("User", userDto.getName());
        }
        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));

        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        User updatedUser = this.userRepo.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }




    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map((user) ->
                modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUser(long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }

    @Override
    public UserDto updateUserRole(Long userId, int roleId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));
        user.getRoles().clear();
        user.getRoles().add(role);
        User updatedUser = userRepo.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto addRoleToUser(Long userId, int roleId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));
        if (user != null && role != null) {
            user.getRoles().add(role);
            userRepo.save(user);
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User findById(long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User findByName(String userName) {
        return userRepo.findByName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User", "name", userName));
    }

}
