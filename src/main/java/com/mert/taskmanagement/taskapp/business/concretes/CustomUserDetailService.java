package com.mert.taskmanagement.taskapp.business.concretes;

import com.mert.taskmanagement.taskapp.core.config.jwt.CustomUserDetail;
import com.mert.taskmanagement.taskapp.dao.UserRepo;
import com.mert.taskmanagement.taskapp.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username).orElseThrow();
        return new CustomUserDetail(user);
    }

}
