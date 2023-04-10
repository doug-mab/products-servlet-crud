package br.com.market.service;

import br.com.market.model.User;

public class AuthenticationService {
	Session<User> db = DataBase.connect("users");
	private User lastValidatedUser;

	public boolean isUserValid(String username, String password) {
		User tryUser = db.findFirstWhere(user -> user.getUsername().equals(username));
		if (tryUser == null)
			return false;
		if (!tryUser.validatePassword(password))
			return false;

		this.lastValidatedUser = tryUser;
		return true;
	}

	public User getLastValidatedUser() {
		return lastValidatedUser;
	}

}
