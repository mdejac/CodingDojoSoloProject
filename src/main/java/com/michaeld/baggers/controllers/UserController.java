package com.michaeld.baggers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.michaeld.baggers.models.User;
import com.michaeld.baggers.services.UserService;
import com.michaeld.baggers.validators.LoginUser;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, @RequestParam("profilePictureFileUrlForm") MultipartFile incomingFile,
							Model model, HttpSession session) {
			
		User user = userService.register(newUser, result, incomingFile);
		
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			model.addAttribute("renderRegisterForm", true);
			return "loginAndReg.jsp";
		}
		
		session.setAttribute("userId", user.getId());
		return "redirect:/profile";
	}
	
	@PostMapping("/login")
	public String loginSubmit(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result,
							Model model, HttpSession session) {
		
		User user = userService.login(newLogin, result);
		
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "loginAndReg.jsp";
		}
		
		session.setAttribute("userId", user.getId());
		return "redirect:/profile";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}	
}
