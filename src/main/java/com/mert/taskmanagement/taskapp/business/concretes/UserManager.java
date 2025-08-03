package com.mert.taskmanagement.taskapp.business.concretes;

import com.mert.taskmanagement.taskapp.business.abstracts.IUserService;
import com.mert.taskmanagement.taskapp.core.exception.NotFoundException;
import com.mert.taskmanagement.taskapp.core.utils.Msg;
import com.mert.taskmanagement.taskapp.dao.UserRepo;
import com.mert.taskmanagement.taskapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements IUserService {

    private final UserRepo userRepo;
    private  final PasswordEncoder passwordEncoder;

    public UserManager(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        String notHashed =user.getPassword();
        String hashed=passwordEncoder.encode(notHashed);
        user.setPassword(hashed);
        return this.userRepo.save(user);
    }

    @Override
    public User get(int id) {
        return this.userRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public User update(User user) {
        this.get(user.getId());
        return this.userRepo.save(user);
    }

    @Override
    public Page<User> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.userRepo.findAll(pageable);
    }

    @Override
    public boolean delete(int id) {
        User deletedUser=this.get(id);
        this.userRepo.delete(deletedUser);
        return true;
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı: " + email));
    }
}
