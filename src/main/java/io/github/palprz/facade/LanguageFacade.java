package io.github.palprz.facade;

import java.util.List;

import io.github.palprz.entity.Language;

public interface LanguageFacade {

	/**
	 * Get all Language entities.
	 *
	 * @return Collection with Language entity
	 */
	public List<Language> getAllLanguage();

	/**
	 * Get a Language entity by provided name.
	 *
	 * @param name Provided name
	 * @return Language connected with provided name
	 */
	public Language getLanguageByName( String name );

	/**
	 * Add a Language entity to the database.
	 *
	 * @param language The entity to save.
	 */
	public void addLanguage( Language language );

	/**
	 * Edit a Language entity by provided new name.
	 *
	 * @param oldLang The existing old Language entity.
	 * @param newName The new name for Language entity.
	 */
	public void updateLanguage( Language oldLang, String newName );

	/**
	 * Remove a Language entity from database.
	 *
	 * @param language The entity to remove.
	 */
	public void removeLanguage( Language language );
}
