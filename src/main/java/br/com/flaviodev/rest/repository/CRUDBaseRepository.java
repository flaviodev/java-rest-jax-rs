package br.com.flaviodev.rest.repository;

import java.util.Collection;

import br.com.flaviodev.rest.model.Question;

public interface CRUDBaseRepository<ID, T> {
	Collection<Question> getAll();

	T get(ID id);

	T save(T item);

	boolean delete(ID id);
}
