package io.github.palprz.facade;

import java.util.List;

import io.github.palprz.entity.WordMap;

public interface WordMapFacade {

	/**
	 * Add a WordMap document to the database.
	 *
	 * @param wordMap The WordMap entity to add
	 */
	public void add( final WordMap wordMap );

	/**
	 * Get all WordMap entities from database.
	 *
	 * @return Collection with WordMap entities.
	 */
	public List<WordMap> getAll();
}
