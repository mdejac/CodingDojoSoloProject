package com.michaeld.baggers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.michaeld.baggers.models.User;
import com.michaeld.baggers.validators.LoginUser;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			return "redirect:/profile";
		}
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "loginAndReg.jsp";
	}
	

}
