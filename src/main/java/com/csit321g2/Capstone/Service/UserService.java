package com.csit321g2.Capstone.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
		user.setDeleted(false);

        return urepo.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return urepo.findAll();
    }

    @SuppressWarnings("finally")
	public UserEntity updateUser(long uid, UserEntity newuserDetails) {
		UserEntity user = new UserEntity();
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

		try {
			
			//search id b4 update
			user = urepo.findById(uid).get();
			
			//update
			user.setFname(newuserDetails.getFname());
			user.setLname(newuserDetails.getLname());
			user.setUsername(newuserDetails.getUsername());

			if(newuserDetails.getPassword() != null) {
				String encryptedPwd = bcrypt.encode(newuserDetails.getPassword());
				user.setPassword(encryptedPwd);
			}
			
		} catch (NoSuchElementException ex){
			throw new NoSuchElementException("user " + uid + " does not exist!");
		} finally {
			return urepo.save(user);
		}
	}
	
	public String deleteUser(long uid) {
		String msg = "";
		
		try {
			Optional<UserEntity> userOptional = urepo.findById(uid);
			if (userOptional.isPresent()) {
				UserEntity user = userOptional.get();
				user.setDeleted(true);
				msg = "User with ID " + uid + " is successfully deleted!";
			} else {
				msg = "User with ID " + uid + " does not exist.";
			}
		} catch (Exception e) {
			msg = "An error occurred while deleting the user with ID " + uid + ": " + e.getMessage();
		}
		
		return msg;
	}
	
    public boolean validateUserCredentials(String username, String password) {

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		UserEntity user = urepo.findByUsername(username);
        
        if (user != null && bcrypt.matches(password, user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

	public UserEntity loginTest(@RequestBody UserEntity loginData) {
        String username = loginData.getUsername();
        String password = loginData.getPassword();

        boolean isValidCredentials = validateUserCredentials(username, password);

        UserEntity user = new UserEntity();
		UserEntity test = new UserEntity();

        if (isValidCredentials) {
            user = urepo.findByUsername(username);
			test.setUid(user.getUid());
			test.setFname(user.getFname());
			test.setLname(user.getLname());
			test.setUsername(user.getUsername());
			test.setType(user.getType());

			return test;
        } else {
            return null;
        }
    }

}
