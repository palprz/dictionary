package io.github.palprz.facade;

import java.util.List;

import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;

public interface WordMapFacade {

	/**
	 * Add a WordMap entity to the database.
	 *
	 * @param wordMap The WordMap entity to add
	 */
	public void addWordMap( WordMap wordMap );

	/**
	 * Get all WordMap entities from database related with provided searched word.
	 *
	 * @param searchWord The search word connected with WordMaps.
	 * @return Collection with WordMap entities.
	 */
	public List<WordMap> getWordMapBySearchWord( Word searchWord );

	/**
	 * Get WordMap entity from database related with provided searched word and translation.
	 *
	 * @param searchWord  The Word entity connected with WordMap.
	 * @param translation The Word entity connected with WordMap.
	 * @return WordMap entity.
	 */
	public WordMap getWordMapBySearchWordAndTranslation(
			Word searchWord, Word translation );

	/**
	 * Update WordMap entity with new details.
	 *
	 * @param wordMap The WordMap entity to change.
	 * @param newSearch The new search word.
	 * @param newTranslation The new translation.
	 */
	public void updateWordMap( WordMap wordMap, Word newSearch, Word newTranslation );

	/**
	 * Remove WordMap from database with provided details.
	 *
	 * @param searchWord The Word entity connected with WordMap
	 * @param translation The Word entity connected with WordMap.
	 */
	public void removeWordMap( Word searchWord, Word translation );
}
