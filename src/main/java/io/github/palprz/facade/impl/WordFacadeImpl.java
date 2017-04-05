package io.github.palprz.facade.impl;

import io.github.palprz.db.Database;
import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;
import io.github.palprz.facade.WordFacade;

public class WordFacadeImpl implements WordFacade {

	private static final LanguageFacadeImpl test = new LanguageFacadeImpl();

	@Override
	public Word addWord( final String name ) {
		final Word word = new Word( name, null );
		Database.getDataStore().save( word );
		return word;
	}

	@Override
	public Word getWordByNameAndLanguage( final String name, final Language language ) {
		return Database.getDataStore().createQuery( Word.class )
				.field( "name" ).equal( name )
				.field( "lang" ).equal( language ).get();
	}
}
