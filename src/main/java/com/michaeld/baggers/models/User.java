package com.michaeld.baggers.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@NotBlank(message="Field required.")
	private String firstName;
	
	@NotBlank(message="Field required.")
	private String lastName;
	
	@NotNull(message="Field required.")
	@Past(message="Birthday must be in the past.")
	@Column(columnDefinition="DATE")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;

	@NotEmpty(message="Field required.")
	@Email(message="Valid email address Field required.")
	private String email;

	@NotBlank(message="Field required.")
	@Size(min=8, message="Password must be at least eight characters.")
	private String password;
	
	@Transient
	@NotBlank(message="Field required.")
	@Size(min=8, message="Password must be at least eight characters.")
	private String confirmPassword;
	
	@OneToMany(mappedBy="createdBy", fetch=FetchType.LAZY)
	private List<Tournament> createdTournaments;
	
	@OneToMany(mappedBy="requestor", fetch=FetchType.LAZY)
	private List<Friend> outgoingFriendRequests;
	
	@OneToMany(mappedBy="recipient", fetch=FetchType.LAZY)
	private List<Friend> incomingFriendRequests;
	
	@ManyToMany
	@JoinTable(
			name="tournamentRegistry",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="tournament_id")
	)
	private List<Tournament> tournamentsRegistered;
	
	private String clubName;
	
	private String profilePictureFileUrl = "";
			
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	public User() {}
	
	@PrePersist
	protected void onCreate(){
		this.clubName = "Representing Themselves";
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Tournament> getCreatedTournaments() {
		return createdTournaments;
	}

	public void setCreatedTournaments(List<Tournament> createdTournaments) {
		this.createdTournaments = createdTournaments;
	}

	public List<Tournament> getTournamentsRegistered() {
		return tournamentsRegistered;
	}

	public void setTournamentsRegistered(List<Tournament> tournamentsRegistered) {
		this.tournamentsRegistered = tournamentsRegistered;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getProfilePictureFileUrl() {
		return profilePictureFileUrl;
	}

	public void setProfilePictureFileUrl(String profilePictureFileUrl) {
		this.profilePictureFileUrl = profilePictureFileUrl;
	}

	public List<Friend> getOutgoingFriendRequests() {
		return outgoingFriendRequests;
	}

	public void setOutgoingFriendRequests(List<Friend> outgoingFriendRequests) {
		this.outgoingFriendRequests = outgoingFriendRequests;
	}

	public List<Friend> getIncomingFriendRequests() {
		return incomingFriendRequests;
	}

	public void setIncomingFriendRequests(List<Friend> incomingFriendRequests) {
		this.incomingFriendRequests = incomingFriendRequests;
	}
}
