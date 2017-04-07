package io.github.palprz.facade;

import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;

public interface WordFacade {

	/**
	 * Add a Word entity to the database.
	 *
	 * @param name The name of Word entity.
	 * @param language The language of Word entity.
	 */
	public void addWord( String name, Language language );

	/**
	 * Get a Word by name from database.
	 *
	 * @param name The name of Word.
	 * @param language The language of Word.
	 * @return Word
	 */
	public Word getWordByNameAndLanguage( String name, Language language );
}
