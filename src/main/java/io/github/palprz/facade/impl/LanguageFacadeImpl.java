package io.github.palprz.facade.impl;

import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import io.github.palprz.db.Database;
import io.github.palprz.entity.Language;
import io.github.palprz.facade.LanguageFacade;

public class LanguageFacadeImpl implements LanguageFacade {

	@Override
	public Language addLanguage( final String name ) {
		final Language lang = new Language( name );
		Database.getDataStore().save( lang );
		return lang;
	}

	@Override
	public void updateLanguage( final String oldName, final String newName ) {
		final Query<Language> query = Database.getDataStore().createQuery( Language.class )
				.field( "name" ).equal( oldName );

		final UpdateOperations<Language> updateQuery = Database.getDataStore().createUpdateOperations( Language.class )
				.set( "name", newName );

		Database.getDataStore().update( query, updateQuery );
	}
}
