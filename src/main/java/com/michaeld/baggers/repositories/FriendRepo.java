package com.michaeld.baggers.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.michaeld.baggers.models.Friend;
import com.michaeld.baggers.models.User;

@Repository
public interface FriendRepo extends CrudRepository<Friend, Long> {
	List<Friend> findByRecipient(User recipient);
	List<Friend> findByRequestor(User requestor);
	List<Friend> findByRecipientAndStatus(User recipient, String Status);
	List<Friend> findByRequestorAndStatus(User recipient, String Status);
}
