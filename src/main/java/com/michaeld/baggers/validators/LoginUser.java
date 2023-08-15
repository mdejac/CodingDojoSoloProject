package com.michaeld.baggers.validators;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class LoginUser {

	@NotEmpty(message="Field required.")
	@Email(message="Valid email address required.")
	private String email;
	
	@NotBlank(message="Field required.")
	private String password;
	
	public LoginUser() {}

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
}
