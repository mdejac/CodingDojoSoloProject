package com.michaeld.baggers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michaeld.baggers.models.Tournament;
import com.michaeld.baggers.models.User;
import com.michaeld.baggers.repositories.TournamentRepo;

@Service
public class TournamentService {

	@Autowired
	private TournamentRepo tournamentRepo;
	
	public Tournament create(Tournament t, User u) {
		t.setCreatedBy(u);
		t.setEventRegisteredPlayersCount(0);
		return tournamentRepo.save(t);
	}
	
	public Tournament getById(Long id) {
		return tournamentRepo.findById(id).orElse(null);
	}
	
	public List<Tournament> getAll() {
		return tournamentRepo.findAll();
	}
	
	public List<Tournament> getAllByEventDateSort() {
		return tournamentRepo.findAllSortedByEventDateAndTime();
	}
	
	public List<Tournament> getAllEventsByUserId(Long userId) {
		return tournamentRepo.findCombinedTournamentsByUserId(userId);
	}
	
	public Tournament update(Tournament t) {
		Tournament originalTournament = getById(t.getId());
		t.setCreatedBy(originalTournament.getCreatedBy());
		t.setEventRegisteredPlayersCount(originalTournament.getEventRegisteredPlayersCount());
		t.setEventRegisteredPlayers(originalTournament.getEventRegisteredPlayers());
		return tournamentRepo.save(t);
	}
	
	public void delete(Long id) {
		Tournament tournament = getById(id);
		tournament.setEventRegisteredPlayers(null);
		tournamentRepo.deleteById(id);
	}
	
	public void registerUser(Tournament t, User u) {
		if (!t.getEventRegisteredPlayers().contains(u) && t.getEventRegisteredPlayersCount() < t.getEventMaxPlayerCount()) {
			t.getEventRegisteredPlayers().add(u);
			t.setEventRegisteredPlayersCount(t.getEventRegisteredPlayersCount()+1);
			tournamentRepo.save(t);
		}
	}
	
	public void unregisterUser(Tournament t, User u) {
		if (t.getEventRegisteredPlayers().contains(u)) {
			t.getEventRegisteredPlayers().remove(u);
			t.setEventRegisteredPlayersCount(t.getEventRegisteredPlayersCount()-1);
			tournamentRepo.save(t);
		}
	}
	
	public boolean isAuthorized (Long tournamentId, Long userId) {
		Tournament currentTournament = getById(tournamentId);
		if (currentTournament == null || !(currentTournament.getCreatedBy().getId().equals(userId))) {
			return false;
		}
		return true;
	}
	
	public boolean hasEntry(Long tournamentId) {
		Tournament currentTournament = getById(tournamentId);
		if (currentTournament == null) {
			return false;
		}
		return true;
	}
}
