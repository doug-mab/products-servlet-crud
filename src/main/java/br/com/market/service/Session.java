package br.com.market.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import br.com.market.model.DataBaseIdentifiable;

public class Session<T> {
	private Map<Integer, T> table = new HashMap<>();

	public void create(DataBaseIdentifiable newRow) {
		table.put(newRow.getId(), (T) newRow);
		System.out.println("Successfully created! ID: " + newRow.getId());
	}

	public T findById(int id) {
		T row = table.get(id);
		if (row == null)
			throw new IllegalArgumentException("Invalid ID.");
		return row;
	}

	public List<T> findAll() {
		List<T> sessions = table.values().stream().toList();
		return sessions;
	}

	public List<T> findWhere(Predicate<T> where) {
		return this.findAll().stream().filter(where).toList();
	}

	public T findFirstWhere(Predicate<T> where) {
		List<T> rows = this.findWhere(where);
		if (rows.isEmpty())
			return null;
		return rows.get(0);
	}

	public void deleteById(int id) {
		table.remove(id);
	}
}
