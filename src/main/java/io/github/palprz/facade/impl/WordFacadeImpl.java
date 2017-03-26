package io.github.palprz.facade.impl;

import io.github.palprz.db.Database;
import io.github.palprz.entity.Word;
import io.github.palprz.facade.WordFacade;

public class WordFacadeImpl implements WordFacade {

	@Override
	public Word addWord( final String name ) {
		final Word word = new Word( name );
		Database.getDataStore().save( word );
		return word;
	}

	@Override
	public Word getWordByName( final String name ) {
		return Database.getDataStore().createQuery( Word.class ).field( "name" ).equal( name ).get();
	}
}
