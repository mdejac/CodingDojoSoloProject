package com.michaeld.baggers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michaeld.baggers.models.Tournament;
import com.michaeld.baggers.models.User;
import com.michaeld.baggers.services.TournamentService;
import com.michaeld.baggers.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {
	
	@Autowired
	private TournamentService tournamentService;
	
	@Autowired
	private UserService userService;

	@GetMapping("")
	public String listAll(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		User user = userService.getById((Long) session.getAttribute("userId"));
		model.addAttribute("user", user);
		model.addAttribute("selected", "findTournament");
		model.addAttribute("allTournaments", tournamentService.getAllByEventDateSort());
		return "tournamentViewAll.jsp";
	}
	
	@GetMapping("/create")
	public String createTournament(@ModelAttribute("tournament") Tournament tournament, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		model.addAttribute("user", userService.getById((Long) session.getAttribute("userId")));
		model.addAttribute("selected", "createTournament");
		return "tournamentCreate.jsp";
	}
	
	@PostMapping("/create")
	public String createTrounamentSubmit(@Valid @ModelAttribute("tournament") Tournament tournament, BindingResult result, HttpSession session, Model model) {
		User user = userService.getById((Long) session.getAttribute("userId"));
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("selected", "createTournament");			
			return "tournamentCreate.jsp";
		}
		tournamentService.create(tournament, user);
		return "redirect:/profile";
	}
	
	@GetMapping("/{tournamentId}/view")
	public String viewTournament(@PathVariable("tournamentId") Long tournamentId, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (!tournamentService.hasEntry(tournamentId)) {
			return "redirect:/tournaments";
		}
		model.addAttribute("user", userService.getById((Long) session.getAttribute("userId")));
		model.addAttribute("tournament", tournamentService.getById(tournamentId));
		return "tournamentViewOne.jsp";
	}
	
	@GetMapping("/{tournamentId}/edit")
	public String editTournament(@PathVariable("tournamentId") Long tournamentId, Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (!tournamentService.isAuthorized(tournamentId, (Long)session.getAttribute("userId"))) {
			return "redirect:/tournaments";
		}
		model.addAttribute("tournament", tournamentService.getById(tournamentId));
		return "tournamentEdit.jsp";
	}
	
	@PutMapping("/{id}/edit")
	public String editTournament(@Valid @ModelAttribute("tournament") Tournament tournament, BindingResult result, @PathVariable("id") Long id, HttpSession session) {
		if (result.hasErrors()) {
			return "tournamentEdit.jsp";
		}
		tournamentService.update(tournament);
		return "redirect:/tournaments/"+id+"/view";
	}
	
	@GetMapping("/{tournamentId}/delete")
	public String delete(@PathVariable("tournamentId") Long tournamentId, HttpSession session) {	
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if (!tournamentService.isAuthorized(tournamentId, (Long)session.getAttribute("userId"))) {
			return "redirect:/profile";
		}
		tournamentService.delete(tournamentId);
		return "redirect:/tournaments";
	}
	
	@GetMapping("/{tournamentId}/register")
	public String registerForTournament(@PathVariable("tournamentId") Long tournamentId, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		tournamentService.registerUser(tournamentService.getById(tournamentId), userService.getById((Long) session.getAttribute("userId")));
		return "redirect:/tournaments/"+tournamentId+"/view";
	}
	
	@GetMapping("/{tournamentId}/unregister")
	public String unregisterForTournament(@PathVariable("tournamentId") Long tournamentId, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		tournamentService.unregisterUser(tournamentService.getById(tournamentId), userService.getById((Long) session.getAttribute("userId")));
		return "redirect:/tournaments/"+tournamentId+"/view";
	}
}
