package com.mert.taskmanagement.taskapp.business.abstracts;

import com.mert.taskmanagement.taskapp.entities.User;
import org.springframework.data.domain.Page;

public interface IUserService {

    User save(User user);

    User get(int id);

    User update(User user);

    Page<User> findAll(int page,int pageSize);

    boolean delete(int id);

    User findByEmail(String email);

}
