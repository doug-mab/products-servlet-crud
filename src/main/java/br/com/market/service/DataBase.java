package br.com.market.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.com.market.model.Product;
import br.com.market.model.User;

public class DataBase {
	private static Map<String, Session<?>> sessions = new HashMap<>();
	private static int availableId = 0;

	static {
		User exampleUser = new User("Jackie", "12345");
		DataBase.connect("users").create(exampleUser);

		Session<Product> session = DataBase.connect("products");
		session.create(new Product("Display HD", new BigDecimal("499.99"), exampleUser));
		session.create(new Product("Fan", new BigDecimal("119.99"), exampleUser));
		session.create(new Product("Book", new BigDecimal("24.99"), exampleUser));
		session.create(new Product("Green Hat", new BigDecimal("19.99"), exampleUser));
	}

	public static <T> Session<T> connect(String sessionName) {
		Session<T> session = (Session<T>) sessions.get(sessionName);
		if (session == null) {
			session = new Session<T>();
			sessions.put(sessionName, session);
		}
		return session;
	}

	public static int getNewId() {
		return availableId++;
	}
}
