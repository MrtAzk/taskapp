package com.mert.taskmanagement.taskapp.api;

import com.mert.taskmanagement.taskapp.business.concretes.AuthenticationService;
import com.mert.taskmanagement.taskapp.dto.request.user.UserAuthRequest;
import com.mert.taskmanagement.taskapp.dto.request.user.UserSaveRequest;
import com.mert.taskmanagement.taskapp.dto.response.user.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserSaveRequest userSaveRequest){

        return ResponseEntity.ok(authenticationService.save(userSaveRequest));

    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody UserAuthRequest userAuthRequest){

        return ResponseEntity.ok(authenticationService.auth(userAuthRequest));

    }

}
