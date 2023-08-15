package com.michaeld.baggers.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.michaeld.baggers.models.Tournament;

@Repository
public interface TournamentRepo extends CrudRepository<Tournament, Long> {
	List<Tournament> findAll();
	
	@Query("SELECT t FROM Tournament t ORDER BY t.eventDate ASC, t.eventTime ASC")
	List<Tournament> findAllSortedByEventDateAndTime();
	
    // Custom query to get all tournaments created by a user
    @Query("SELECT t FROM Tournament t WHERE t.createdBy.id = :userId ORDER BY t.eventDate ASC, t.eventTime ASC")
    List<Tournament> findCreatedTournamentsByUserId(@Param("userId") Long userId);

    // Custom query to get all tournaments a user is registered for
    @Query("SELECT t FROM Tournament t JOIN t.eventRegisteredPlayers p WHERE p.id = :userId ORDER BY t.eventDate ASC, t.eventTime ASC")
    List<Tournament> findRegisteredTournamentsByUserId(@Param("userId") Long userId);

    // Combined method to fetch both created and registered tournaments
    default List<Tournament> findCombinedTournamentsByUserId(Long userId) {
        List<Tournament> createdTournaments = findCreatedTournamentsByUserId(userId);
        List<Tournament> registeredTournaments = findRegisteredTournamentsByUserId(userId);

        // Merge the lists and remove duplicates using a Set
        Set<Tournament> combinedSet = new HashSet<>(createdTournaments);
        combinedSet.addAll(registeredTournaments);

        // Convert the Set back to a List
        return combinedSet.stream().collect(Collectors.toList());
    }
}
