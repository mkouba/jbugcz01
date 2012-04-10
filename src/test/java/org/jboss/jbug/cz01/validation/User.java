package org.jboss.jbug.cz01.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

	private String name;

	public User() {
	}

	public User(String name) {
		super();
		this.name = name;
	}

	@NotNull
	@Size(max = 10, message = "ERROR")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
