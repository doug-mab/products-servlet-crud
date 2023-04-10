package br.com.market.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product extends DataBaseIdentifiable {
	private String name;
	private BigDecimal price;
	private LocalDate postDate;
	private User poster;

	public Product(String name, BigDecimal price, User poster) {
		super();
		this.name = name;
		this.price = price;
		postDate = LocalDate.now();
		this.poster = poster;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public User getPoster() {
		return poster;
	}

	public void setName(String name) {
		if (name == null || name.isBlank())
			throw new IllegalArgumentException("Invalid name.");
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		if (price == null)
			throw new IllegalArgumentException("Invalid Price.");
		this.price = price;
	}

}
