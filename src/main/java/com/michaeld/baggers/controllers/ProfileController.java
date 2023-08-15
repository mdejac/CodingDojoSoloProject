package com.michaeld.baggers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michaeld.baggers.models.User;
import com.michaeld.baggers.services.FriendService;
import com.michaeld.baggers.services.TournamentService;
import com.michaeld.baggers.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TournamentService tournamentService;
	
	@Autowired
	private FriendService friendService;
	
	@GetMapping("")
	public String dashboard(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userService.getById(userId));
		model.addAttribute("allTournaments", tournamentService.getAllEventsByUserId(userId));
		model.addAttribute("allFriends", friendService.getAllValidFriends(userId));
		model.addAttribute("selected", "profile");
		return "profile.jsp";
	}
	
	@GetMapping("/{profileId}/view")
	public String userProfile(@PathVariable("profileId") Long profileId, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (profileId.equals((Long) session.getAttribute("userId"))) {
			return "redirect:/profile";
		}
		User userProfile = userService.getById(profileId);
		if (userProfile == null) {
			return "redirect:/profile";
		}
		model.addAttribute("user", userProfile);
		model.addAttribute("allTournaments", tournamentService.getAllEventsByUserId(profileId));
		model.addAttribute("allFriends", friendService.getAllFriends(profileId));
		return "profileView.jsp";
	}
}
