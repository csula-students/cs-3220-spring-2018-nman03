package edu.csula.storage.servlet;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import edu.csula.models.User;
import edu.csula.storage.UsersDAO;

/**
 * To abstract the storage access from the business layer using HttpSession
 */
public class UsersDAOImpl implements UsersDAO {
	private final HttpSession context;
	protected static final String CONTEXT_NAME = "users";

	public UsersDAOImpl(HttpSession context) {
		this.context = context;
	}

	@Override
	public boolean authenticate(String username, String password) {
		if (username.equals("admin") && password.equals("cs3220password")) {
			User user = new User(0, username, password);
			context.setAttribute(CONTEXT_NAME, user);

			return true;
		}

		return false;
	}

	@Override
	public Optional<User> getAuthenticatedUser() {
		Object data = context.getAttribute(CONTEXT_NAME);

		if (data == null) {
			return Optional.empty();
		}
		
		return Optional.of((User) data );
	}

	@Override
	public void logout() {
		context.invalidate();
	}
}
