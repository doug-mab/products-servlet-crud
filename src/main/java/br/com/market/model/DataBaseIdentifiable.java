package br.com.market.model;

import br.com.market.service.DataBase;

public abstract class DataBaseIdentifiable {
	private int id;

	public DataBaseIdentifiable() {
		this.id = DataBase.getNewId();
	}

	public int getId() {
		return this.id;
	}
}
