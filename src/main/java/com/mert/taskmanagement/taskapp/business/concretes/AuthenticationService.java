package com.mert.taskmanagement.taskapp.business.concretes;

import com.mert.taskmanagement.taskapp.dao.UserRepo;
import com.mert.taskmanagement.taskapp.dto.request.user.UserAuthRequest;
import com.mert.taskmanagement.taskapp.dto.request.user.UserSaveRequest;
import com.mert.taskmanagement.taskapp.dto.response.user.UserResponse;
import com.mert.taskmanagement.taskapp.entities.User;
import com.mert.taskmanagement.taskapp.entities.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserResponse save(UserSaveRequest userSaveRequest) {


        User user=User.builder().name(userSaveRequest.getName()).password(passwordEncoder.encode(userSaveRequest.getPassword())).role(Role.ROLE_USER).build();
        userRepo.save(user);



        var token=jwtService.generateToken(user);
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .token(token)
                .build();
    }

    public UserResponse auth(UserAuthRequest userAuthRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthRequest.getName(),userAuthRequest.getPassword()));
        User user=userRepo.findByName(userAuthRequest.getName()).orElseThrow();
        String token= jwtService.generateToken(user);
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .token(token)
                .build();
    }
}
