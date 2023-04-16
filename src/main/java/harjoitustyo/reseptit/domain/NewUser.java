package harjoitustyo.reseptit.domain;

import javax.validation.constraints.Size;

public class NewUser {
	
	@Size(min = 3, max = 10, 
	message = "Sallittu pituus 3-10 merkkiä")
	private String username = "";
	
	@Size(min = 5, max = 10,
	message = "Sallittu pituus 5-10 merkkiä")
	private String password = "";
	
	@Size(min = 5, max = 10,
	message = "Sallittu pituus 5-10 merkkiä")
	private String passwordCheck = "";
	
	private String role = "USER";

	public NewUser() {
		super();
	}

	public NewUser(@Size(min = 3, max = 10, message = "Sallittu pituus 3-10 merkkiä") String username,
			@Size(min = 3, max = 10, message = "Sallittu pituus 3-10 merkkiä") String password,
			@Size(min = 3, max = 10, message = "Sallittu pituus 3-10 merkkiä") String passwordCheck,
			String role) {
		super();
		this.username = username;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "newUser [username=" + username + ", password=" + password + ", passwordCheck=" + passwordCheck
				+ ", role=" + role + "]";
	}
	
	

}
