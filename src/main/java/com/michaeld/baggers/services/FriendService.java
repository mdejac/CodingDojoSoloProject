package com.michaeld.baggers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michaeld.baggers.models.Friend;
import com.michaeld.baggers.repositories.FriendRepo;

@Service
public class FriendService {

	@Autowired
	private FriendRepo friendRepo;
	
	@Autowired
	private UserService userService;
	
	public Friend sendRequest(Long recipientId, Long requestorId) {
		for (Friend friend : getAllFriends(requestorId)) {
			if (friend.getRecipient().getId().equals(recipientId)) {
				return null;
			}
		}
		Friend f = new Friend();
		f.setRecipient(userService.getById(recipientId));
		f.setRequestor(userService.getById(requestorId));
		f.setStatus("PENDING");
		return friendRepo.save(f);
	}
	
	public Friend getById(Long id) {
		return friendRepo.findById(id).orElse(null);
	}
	
	public List<Friend> getAllFriends(Long userId) {
		List<Friend> allFriends = friendRepo.findByRecipient(userService.getById(userId));
		allFriends.addAll(friendRepo.findByRequestor(userService.getById(userId)));
		return allFriends; 
	}
	
	public List<Friend> getAllValidFriends(Long userId) {
		List<Friend> allFriends = friendRepo.findByRecipientAndStatus(userService.getById(userId), "PENDING");
		allFriends.addAll(friendRepo.findByRecipientAndStatus(userService.getById(userId), "ACCEPT"));
		allFriends.addAll(friendRepo.findByRequestorAndStatus(userService.getById(userId), "ACCEPT"));
		return allFriends; 
	}
	
	public Friend acceptRequest(Friend f) {
		f.setStatus("ACCEPT");
		return friendRepo.save(f);
	}
	
	public void removeFriend(Long id) {
		friendRepo.deleteById(id);
	}
	
	public boolean isValidId(Long friendId) {
		Friend friend = getById(friendId);
		return friend != null ? true : false;
	}
}
