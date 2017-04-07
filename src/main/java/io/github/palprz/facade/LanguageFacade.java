package io.github.palprz.facade;

import io.github.palprz.entity.Language;

public interface LanguageFacade {

	/**
	 * Get a Language entity by provided name.
	 * @param name Provided name
	 * @return Language connected with provided name
	 */
	public Language getLanguageByName( String name );

	/**
	 * Add a Language entity to the database.
	 *
	 * @param name The name of Language entity.
	 */
	public void addLanguage( String name );

	/**
	 * Edit a Language entity by provided new name.
	 *
	 * @param oldName The existing old name in Language entity.
	 * @param newName The new name for Language entity.
	 */
	public void updateLanguage( String oldName, String newName );
}
