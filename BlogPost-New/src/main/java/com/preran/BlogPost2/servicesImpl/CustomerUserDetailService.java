package com.preran.BlogPost2.servicesImpl;

import com.preran.BlogPost2.exceptions.ResourceNotFoundException;
import com.preran.BlogPost2.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userID : ", username));
    }
}
