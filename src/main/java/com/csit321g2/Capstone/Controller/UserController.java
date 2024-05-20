package com.csit321g2.Capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.Capstone.Entity.UserEntity;
import com.csit321g2.Capstone.Service.UserService;


@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://10.241.126.247:5173"})
public class UserController {

    @Autowired
    UserService userv;

    @PostMapping("/registerUser")
    public UserEntity registerUser(@RequestBody UserEntity user) {
        return userv.registerUser(user);
    }

    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return userv.getAllUsers();
    }

    @PutMapping("/updateUser/{uid}")
    public UserEntity updateUser(@PathVariable int uid, @RequestBody UserEntity newUserDetails) {
        return userv.updateUser(uid, newUserDetails);
    }

	
	@DeleteMapping("/deleteUser/{uid}")
    public String deleteUser(@PathVariable Long uid) {
        if (uid == null) {
            return "Invalid user ID";
        }
        return userv.deleteUser(uid);
    }
    
    @PostMapping("/login")
    public UserEntity loginTest(@RequestBody UserEntity loginData) {
        return userv.loginTest(loginData);
    }
}
