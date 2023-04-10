package br.com.market.model;

public class User extends DataBaseIdentifiable {
	private String username;
	private String password;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}

	public String getUsername() {
		return username;
	}

}
