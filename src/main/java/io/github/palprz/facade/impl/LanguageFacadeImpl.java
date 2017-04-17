package io.github.palprz.facade.impl;

import java.util.List;

import org.mongodb.morphia.query.UpdateOperations;

import io.github.palprz.db.Database;
import io.github.palprz.entity.Language;
import io.github.palprz.facade.LanguageFacade;

public class LanguageFacadeImpl implements LanguageFacade {

	private static final String NAME_FIELD = "name";

	@Override
	public List<Language> getAllLanguage() {
		return Database.getDataStore().createQuery( Language.class ).asList();
	}

	@Override
	public Language getLanguageByName( final String name ) {
		return Database.getDataStore().createQuery( Language.class )
				.field( NAME_FIELD ).equal( name ).get();
	}

	@Override
	public void addLanguage( final Language language ) {
		Database.getDataStore().save( language );
	}

	@Override
	public void updateLanguage( final Language oldLang, final String newName ) {
		final UpdateOperations<Language> updateQuery = Database.getDataStore().createUpdateOperations( Language.class )
				.set( NAME_FIELD, newName );

		Database.getDataStore().update( oldLang, updateQuery );
	}

	@Override
	public void removeLanguage( final Language language ) {
		Database.getDataStore().delete( language );
	}
}
