package io.github.palprz.facade.impl;

import java.util.List;

import io.github.palprz.db.Database;
import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;
import io.github.palprz.facade.WordFacade;

public class WordFacadeImpl implements WordFacade {

	private static final String NAME_FIELD = "name";
	private static final String LANG_FIELD = "lang";

	@Override
	public void addWord( final Word word ) {
		Database.getDataStore().save( word );
	}

	@Override
	public Word getWordByNameAndLanguage( final String name, final Language language ) {
		return Database.getDataStore().createQuery( Word.class )
				.field( NAME_FIELD ).equal( name )
				.field( LANG_FIELD ).equal( language ).get();
	}

	@Override
	public List<Word> getWordByLanguage( final Language language ) {
		return Database.getDataStore().createQuery( Word.class )
				.field( LANG_FIELD ).equal( language ).asList();
	}

	@Override
	public void removeWord( final Word word ) {
		Database.getDataStore().delete( word );
	}
}
