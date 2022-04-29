package com.javaproject.finalproject.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.javaproject.finalproject.models.LoginUser;
import com.javaproject.finalproject.models.User;
import com.javaproject.finalproject.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    // This method will be called from the controller
    // whenever a user submits a registration form.
    
    public User register(User newUser, BindingResult result)
    {
    	Optional<User> potentialEmail = userRepo.findByEmail(newUser.getEmail());
    	
    	if(potentialEmail.isPresent())
    	{
    		result.rejectValue("email", "Exists", "This email is already being used");
    	}
    	
    	if(!newUser.getPassword().equals(newUser.getConfirm()))
    	{
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}

    	if(result.hasErrors()) {
    	    // Exit the method and go back to the controller 
    	    // to handle the response
    	    return null;
    	}
    	
    	String passwordEntered = newUser.getPassword();
    	String hashed = BCrypt.hashpw(passwordEntered, BCrypt.gensalt());
    	
    	newUser.setPassword(hashed);
    	
		return userRepo.save(newUser);
    
    }
    
    public User findUserByEmail(String email)
    {
    	return userRepo.findByEmail(email).orElse(null);
    }
    
    public User login(LoginUser newLoginObject, BindingResult result)
    {
    	
    	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
    	
    	if(!potentialUser.isPresent())
    	{
    		result.rejectValue("email", "Exists", "INVALID CREDENTIALS");
    		return null;
    	}
    	
    	User user = this.findUserByEmail(newLoginObject.getEmail());
    	
		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword()))
    	{
    	    result.rejectValue("password", "Matches", "Invalid Password!");
    	    return null;
    	}

		return user;
    }

}
