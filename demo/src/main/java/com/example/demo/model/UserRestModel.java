package com.example.demo.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRestModel {
	@NotNull(message = "no debe ser null")
	@Email(message = "el email no es correcto")
	private String email;
	@NotNull(message = "no debe ser null")
	@Size(min = 3, message = "")
	private String firstname;
	@NotNull(message = "no debe ser null")
	private String lastName;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
