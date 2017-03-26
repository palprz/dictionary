package io.github.palprz.facade;

import java.util.List;

import io.github.palprz.entity.WordMap;

public interface WordMapFacade {

	/**
	 * Add a WordMap entity to the database.
	 *
	 * @param wordMap The WordMap entity to add
	 */
	public void addWordMap( final WordMap wordMap );

	/**
	 * Get all WordMap entities from database.
	 *
	 * @return Collection with WordMap entities.
	 */
	public List<WordMap> getWordMap();

	/**
	 * Get all WordMap entities from database related with provided searched word.
	 *
	 * @return Collection with WordMap entities.
	 */
	public List<WordMap> getWordMapBySearchWord( final String searchWord );

	/**
	 * Get WordMap entity from database related with provided searched word and translation.
	 *
	 * @param searchWord  The string with search word name.
	 * @param translation The string with translation name.
	 * @return WordMap entity.
	 */
	public WordMap getWordMapBySearchWordAndTranslation( final String searchWord, final String translation );

	/**
	 * Update WordMap entity with new details.
	 *
	 * @param wordMap The WordMap entity to change.
	 * @param newSearch The new search word.
	 * @param newTranslation The new translation.
	 */
	public void updateWordMap( final WordMap wordMap, final String newSearch, final String newTranslation );
}
