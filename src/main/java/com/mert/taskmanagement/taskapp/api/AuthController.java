package com.mert.taskmanagement.taskapp.api;

import com.mert.taskmanagement.taskapp.business.abstracts.IUserService;
import com.mert.taskmanagement.taskapp.core.config.modelMapper.IModelMapperService;
import com.mert.taskmanagement.taskapp.core.result.ResultData;
import com.mert.taskmanagement.taskapp.core.utils.CreateResult;
import com.mert.taskmanagement.taskapp.dto.request.auth.LoginRequest;
import com.mert.taskmanagement.taskapp.dto.request.user.UserSaveRequest;
import com.mert.taskmanagement.taskapp.dto.response.user.UserResponse;
import com.mert.taskmanagement.taskapp.entities.User;
import com.mert.taskmanagement.taskapp.entities.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final IUserService userService;
    private final IModelMapperService modelMapper;
    private final AuthenticationManager authenticationManager;

    public AuthController(IUserService userService, IModelMapperService modelMapper,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResultData<UserResponse> register(@Valid @RequestBody UserSaveRequest userSaveRequest) {

            User saveUser = this.modelMapper.forRequest().map(userSaveRequest, User.class);
            saveUser.setCreatedAt(LocalDate.now());
            saveUser.setRole(Role.ROLE_USER); // Default role

            User savedUser = this.userService.save(saveUser);
            UserResponse userResponse = this.modelMapper.forResponse().map(savedUser, UserResponse.class);

            return CreateResult.created(userResponse);

    }

    @PostMapping("/login")
    public ResultData<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest,
                                          HttpServletRequest request) {

            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Create session
            HttpSession session = request.getSession(true);
            session.setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );

            // Get user details
            User user = userService.findByEmail(loginRequest.getEmail());
            UserResponse userResponse = this.modelMapper.forResponse().map(user, UserResponse.class);

            return CreateResult.success(userResponse);

        }

    @PostMapping("/logout")
    public ResultData<String> logout(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            SecurityContextHolder.clearContext();

            return CreateResult.success("Çıkış işlemi başarılı");
        } catch (Exception e) {
            return CreateResult.validateError("Çıkış işlemi başarısız");
        }
    }

    @GetMapping("/me")
    public ResultData<UserResponse> getCurrentUser() {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            User user = userService.findByEmail(email);
            UserResponse userResponse = this.modelMapper.forResponse().map(user, UserResponse.class);

            return CreateResult.success(userResponse);

    }
}