package com.example.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserRestModel {
	@NotNull(message = "no debe ser null")
	@Size(min = 3, message = "")
	private String firstname;
	@NotNull(message = "no debe ser null")
	private String lastName;
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
