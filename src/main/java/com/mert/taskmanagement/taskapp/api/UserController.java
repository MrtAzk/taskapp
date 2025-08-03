package com.mert.taskmanagement.taskapp.api;

import com.mert.taskmanagement.taskapp.business.abstracts.IUserService;
import com.mert.taskmanagement.taskapp.core.config.modelMapper.IModelMapperService;
import com.mert.taskmanagement.taskapp.core.result.ResultData;
import com.mert.taskmanagement.taskapp.core.utils.CreateResult;
import com.mert.taskmanagement.taskapp.dto.request.user.UserSaveRequest;
import com.mert.taskmanagement.taskapp.dto.request.user.UserUpdateRequest;
import com.mert.taskmanagement.taskapp.dto.response.CursorResponse;
import com.mert.taskmanagement.taskapp.dto.response.user.UserResponse;
import com.mert.taskmanagement.taskapp.entities.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final IUserService userService;
    private final IModelMapperService modelMapper;

    public UserController(IUserService userService, IModelMapperService modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
//Bunu kullanmıyoruz artık auth yapıldı
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserResponse save(@Valid @RequestBody UserSaveRequest userSaveRequest){
//        User saveUser =this.modelMapper.forRequest().map(userSaveRequest,User.class);
//        saveUser.setCreatedAt(LocalDate.now());
//        this.userService.save(saveUser);
//
//        UserResponse userResponse =this.modelMapper.forResponse().map(saveUser,UserResponse.class);
//        return userResponse;
//
//    }

    public  UserResponse update (@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        User updatedUser=this.modelMapper.forRequest().map(userUpdateRequest,User.class);
        this.userService.save(updatedUser);

        UserResponse userResponse=this.modelMapper.forResponse().map(updatedUser, UserResponse.class);
        return userResponse;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<UserResponse>> getAll(@RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                           @RequestParam(name = "pagesize",required = false,defaultValue = "10")int pageSize)
    {

        Page<User> users =this.userService.findAll(page,pageSize);

        Page<UserResponse> userResponsePage =users.map(user -> this.modelMapper.forResponse().map(user, UserResponse.class));

        return CreateResult.getAll(userResponsePage);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<UserResponse> get(@PathVariable("id") int id){
        User user=this.userService.get(id);
        UserResponse userResponse=this.modelMapper.forResponse().map(user,UserResponse.class);
        return CreateResult.success(userResponse);
    }

}
