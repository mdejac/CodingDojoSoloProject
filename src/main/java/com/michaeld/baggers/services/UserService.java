package com.michaeld.baggers.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.michaeld.baggers.models.User;
import com.michaeld.baggers.repositories.UserRepo;
import com.michaeld.baggers.validators.LoginUser;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private FileService fService;
	
	public User create(User u) {
		return userRepo.save(u);
	}
	
	public User register(User newUser, BindingResult result, MultipartFile incomingFile) {
		if (isValidEmail(newUser.getEmail()) && !userRepo.findByEmail(newUser.getEmail()).isEmpty()) {
			result.rejectValue("email", "Email", "Email already Registered.");
		}
				
		if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
		    result.rejectValue("password", "Matches", "Must match Confirm Password!");
		    result.rejectValue("confirmPassword", "Matches", "Must match Password!");
		}
		
		if (!ageVerify(newUser.getDateOfBirth())) {
			result.rejectValue("dateOfBirth", "Fail", "Sorry you are too young to register.");
		}
		
		if (!newUser.getPassword().matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&+=])(?=.*[a-z]).{8,}$")) {
			result.rejectValue("password", "Requirement", "Requires one captial, one digit, and one of the following symbols : !@#$%^&+=");
		}
		
		String savePath = "userImageUpload/default-user.png";
		if (!incomingFile.isEmpty()) {
			savePath = fService.save(incomingFile);
			if (savePath == null) {
				result.rejectValue("profilePictureFileUrl", "Error", "Size limit exceeded.");
			}
		}
			
		if (result.hasErrors()) {
			return null;
		}
		
		newUser.setProfilePictureFileUrl(savePath);
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepo.save(newUser);
	}
	
	public User login(LoginUser newLogin, BindingResult result) {
		User user = userRepo.findByEmail(newLogin.getEmail()).orElse(null);
		if (user == null) {
			if (isValidEmail(newLogin.getEmail()) && !newLogin.getEmail().equals("")) {
				result.rejectValue("email", "Email", "Email not registered");				
			}
		} else if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
		    result.rejectValue("password", "Invalid", "Invalid credentials!");
		    result.rejectValue("email", "Invalid", "Invalid credentials!");
		}
		
		if (result.hasErrors()) {
			return null;
		}
		return user;
	}
	
	private boolean ageVerify(Date dateOfBirth) {
	    Calendar calendar = GregorianCalendar.getInstance();
	    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 18);
		return calendar.getTime().after(dateOfBirth);
	}
	
	private boolean isValidEmail(String email) {
		String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+$";
		return email.matches(emailPattern);
	}
	
	public List<User> getAll() {
		return userRepo.findAll();
	}
	
	public User getById(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	
	public User getByEmail(String email) {
		return userRepo.findByEmail(email).orElse(null);
	}
		
	public User update(User u) {
		User orgUser = getById(u.getId());
		u.setCreatedTournaments(orgUser.getCreatedTournaments());
		u.setTournamentsRegistered(orgUser.getTournamentsRegistered());
		return userRepo.save(u);
	}
	
	public void delete(Long id) {
		userRepo.deleteById(id);
	}
	
	public boolean isValidId(Long userId) {
		User user = getById(userId);
		return user != null ? true : false;
	}
}
