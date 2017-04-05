package io.github.palprz.facade;

import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;

public interface WordFacade {

	/**
	 * Add a Word entity to the database.
	 *
	 * @param name The name of Word entity.
	 */
	public Word addWord( String name );

	/**
	 * Get a Word by name from database.
	 *
	 * @param name The name of Word.
	 * @param language The language of Word.
	 * @return Word
	 */
	public Word getWordByNameAndLanguage( String name, Language language );
}
