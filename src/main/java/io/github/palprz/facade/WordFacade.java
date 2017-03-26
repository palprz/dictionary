package io.github.palprz.facade;

import io.github.palprz.entity.Word;

public interface WordFacade {

	/**
	 * Add a Word entity to the database.
	 *
	 * @param name The name of Word entity.
	 */
	public Word addWord( final String name );

	/**
	 * Get a Word by name from database.
	 *
	 * @param name The name of Word.
	 * @return Word
	 */
	public Word getWordByName( final String name );
}
