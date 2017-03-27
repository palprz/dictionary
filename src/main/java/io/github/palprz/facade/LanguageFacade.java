package io.github.palprz.facade;

import io.github.palprz.entity.Language;

public interface LanguageFacade {

	/**
	 * Add a Language entity to the database.
	 *
	 * @param name The name of Language entity.
	 */
	public Language addLanguage( final String name );

	/**
	 * Edit a Language entity by provided new name.
	 *
	 * @param oldName The existing old name in Language entity.
	 * @param newName The new name for Language entity.
	 */
	public void updateLanguage( final String oldName, final String newName );
}
