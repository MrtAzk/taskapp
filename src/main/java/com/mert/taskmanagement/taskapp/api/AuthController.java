package com.mert.taskmanagement.taskapp.api;

import com.mert.taskmanagement.taskapp.business.abstracts.IUserService;
import com.mert.taskmanagement.taskapp.core.config.modelMapper.IModelMapperService;
import com.mert.taskmanagement.taskapp.dto.request.user.UserSaveRequest;
import com.mert.taskmanagement.taskapp.dto.response.user.UserResponse;
import com.mert.taskmanagement.taskapp.entities.User;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

        private final IUserService userService;
        private final PasswordEncoder passwordEncoder;
        private final IModelMapperService modelMapper;

        public AuthController(IUserService userService, PasswordEncoder passwordEncoder, IModelMapperService modelMapper) {
            this.userService = userService;
            this.passwordEncoder = passwordEncoder;
            this.modelMapper = modelMapper;
        }

        @PostMapping("/register")
        public UserResponse register(@Valid @RequestBody UserSaveRequest userSaveRequest) {
            User saveUser = this.modelMapper.forRequest().map(userSaveRequest, User.class);
            saveUser.setCreatedAt(LocalDate.now());
            this.userService.save(saveUser);

            UserResponse userResponse = this.modelMapper.forResponse().map(saveUser, UserResponse.class);
            return userResponse;

        }


        @GetMapping("/login")
        public String loginPage() {
            return "Lütfen giriş yapınız";
        }
    }


