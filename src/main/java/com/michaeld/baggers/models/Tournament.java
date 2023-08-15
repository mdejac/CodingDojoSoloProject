package com.michaeld.baggers.models;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.michaeld.baggers.validators.FutureOrPresentDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tournaments")
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min=8, message="Must be at least eight characters.")
	private String eventName;
	
	@NotBlank
	@Size(min=4, max=100, message="Must be between four and one hundred characters.")
	private String eventLocation;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@FutureOrPresentDate
	private Date eventDate;
	
	@NotNull
	private LocalTime eventTime;
	
	@NotBlank
	private String eventFormat;
	
	@NotBlank
	private String eventTeamType;
	
	@NotNull
	@Min(value=1, message="Must be at least one")
	private Integer eventRoundCount;
	
	@NotNull
	@Min(value=1, message="Must be at least one")
	private Integer eventBoardCount;
	
	@NotNull
	@Min(value=2, message="Must have allow at least two players")
	@Max(value=32, message="Max of 32 players allowed")
	private Integer eventMaxPlayerCount;
	
	private Integer eventRegisteredPlayersCount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User createdBy;
	
	@ManyToMany
	@JoinTable(
		name="tournamentRegistry",
		joinColumns = @JoinColumn(name="tournament_id"),
		inverseJoinColumns = @JoinColumn(name="user_id")
	)
	private List<User> eventRegisteredPlayers;

	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	public Tournament() {}

	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public LocalTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventFormat() {
		return eventFormat;
	}

	public void setEventFormat(String eventFormat) {
		this.eventFormat = eventFormat;
	}

	public String getEventTeamType() {
		return eventTeamType;
	}

	public void setEventTeamType(String eventTeamType) {
		this.eventTeamType = eventTeamType;
	}

	public Integer getEventRoundCount() {
		return eventRoundCount;
	}

	public void setEventRoundCount(Integer eventRoundCount) {
		this.eventRoundCount = eventRoundCount;
	}

	public Integer getEventBoardCount() {
		return eventBoardCount;
	}

	public void setEventBoardCount(Integer eventBoardCount) {
		this.eventBoardCount = eventBoardCount;
	}

	public Integer getEventMaxPlayerCount() {
		return eventMaxPlayerCount;
	}

	public void setEventMaxPlayerCount(Integer eventMaxPlayerCount) {
		this.eventMaxPlayerCount = eventMaxPlayerCount;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getEventRegisteredPlayersCount() {
		return eventRegisteredPlayersCount;
	}

	public void setEventRegisteredPlayersCount(Integer eventRegisteredPlayersCount) {
		this.eventRegisteredPlayersCount = eventRegisteredPlayersCount;
	}

	public List<User> getEventRegisteredPlayers() {
		return eventRegisteredPlayers;
	}

	public void setEventRegisteredPlayers(List<User> eventRegisteredPlayers) {
		this.eventRegisteredPlayers = eventRegisteredPlayers;
	}
}
