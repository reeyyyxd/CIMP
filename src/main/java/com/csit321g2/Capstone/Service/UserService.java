package com.csit321g2.Capstone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.csit321g2.Capstone.Entity.UserEntity;
import com.csit321g2.Capstone.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository urepo;

    public UserEntity registerUser(UserEntity user) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPwd = bcrypt.encode(user.getPassword());
        user.setPassword(encryptedPwd);

        return urepo.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return urepo.findAll();
    }

}
