package io.github.palprz.facade;

import java.util.List;

import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;

public interface WordFacade {

	/**
	 * Add a Word entity to the database.
	 *
	 * @param word The entity to add.
	 */
	public void addWord( Word word );

	/**
	 * Get a Word by name from database.
	 *
	 * @param name The name of Word.
	 * @param language The language of Word.
	 * @return Word
	 */
	public Word getWordByNameAndLanguage( String name, Language language );

	/**
	 * Get a Collection with Word entities by language.
	 *
	 * @param language The language of Word.
	 * @return Collection with Word entities
	 */
	public List<Word> getWordByLanguage( Language language );

	/**
	 * Remove a Word entity from database.
	 *
	 * @param word The entity to remove.
	 */
	public void removeWord( Word word );

	/**
	 * Remove all Word entities in database.
	 */
	public void removeAllWords();
}
