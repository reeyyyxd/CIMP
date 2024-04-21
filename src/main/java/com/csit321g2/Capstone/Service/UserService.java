package com.csit321g2.Capstone.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @SuppressWarnings("finally")
	public UserEntity updateUser(long uid, UserEntity newuserDetails) {
		UserEntity user = new UserEntity();
		try {
			//search id b4 update
			user = urepo.findById(uid).get();
			
			//update
			user.setFname(newuserDetails.getFname());
			user.setLname(newuserDetails.getLname());
			user.setUsername(newuserDetails.getUsername());
			user.setPassword(newuserDetails.getPassword());
			
			
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
				urepo.delete(user);
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
        // Implement logic to check credentials against your database
        // Fetch admin by email and compare passwords (after hashing and salting)

        // Example pseudo-code:
		UserEntity user = urepo.findByUsername(username);
        
        if (user != null && user.getPassword().equals(password)) {
            return true; // Credentials are valid
        } else {
            return false; // Invalid credentials
        }
    }

    public String deleteUser(int aid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    public Optional<UserEntity> findById(long uid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
