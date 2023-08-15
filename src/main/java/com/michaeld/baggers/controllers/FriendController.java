package com.michaeld.baggers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michaeld.baggers.models.Friend;
import com.michaeld.baggers.services.FriendService;
import com.michaeld.baggers.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/friends")
public class FriendController {

	@Autowired
	private FriendService friendService;
	
	@Autowired
	private UserService userService;
		
	@GetMapping("/{recipientId}/new")
	public String sendFriendRequest(@PathVariable("recipientId") Long recipientId, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (!userService.isValidId(recipientId)) {
			return "redirect:/profile";
		}
		friendService.sendRequest(recipientId, (Long) session.getAttribute("userId"));
		return "redirect:/profile/{recipientId}/view";		
	}
	
	@GetMapping("/{friendId}/accept")
	public String acceptFriendRequest(@PathVariable("friendId") Long friendId, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (!friendService.isValidId(friendId)) {
			return "redirect:/profile";
		}
		Long userId = (Long) session.getAttribute("userId");
		Friend friend = friendService.getById(friendId);
		if (!friend.getStatus().equals("PENDING") || !(friend.getRequestor().getId().equals(userId) || friend.getRecipient().getId().equals(userId))) {
			return "redirect:/profile";
		}
		friendService.acceptRequest(friendService.getById(friendId));
		return "redirect:/profile";
	}
	
	@GetMapping("/{friendId}/deny")
	public String denyFriendRequest(@PathVariable("friendId") Long friendId, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (!friendService.isValidId(friendId)) {
			return "redirect:/profile";
		}
		Long userId = (Long) session.getAttribute("userId");
		Friend friend = friendService.getById(friendId);
		if (!(friend.getRequestor().getId().equals(userId) || friend.getRecipient().getId().equals(userId))) {
			return "redirect:/profile";
		}
		friendService.removeFriend(friendId);
		return "redirect:/profile";
	}
}
