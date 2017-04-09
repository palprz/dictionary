package io.github.palprz.facade.impl;

import java.util.Arrays;
import java.util.List;

import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import io.github.palprz.db.Database;
import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordMapFacade;

public class WordMapFacadeImpl implements WordMapFacade {

	private static final String NAME_FIELD = "name";
	private static final String SEARCH_WORD_FIELD = "searchWord";
	private static final String TRANSLATION_FIELD = "translation";

	private static final WordFacadeImpl WORD_FACADE = new WordFacadeImpl();

	@Override
	public void addWordMap( final WordMap wordMap ) {
		final Word searchWord = wordMap.getSearchWord();
		final Word translation = wordMap.getTranslation();

		Database.getDataStore().save( Arrays.asList( searchWord, translation ) );
		Database.getDataStore().save( wordMap );
	}

	@Override
	public List<WordMap> getWordMapBySearchWord( final Word searchWord ) {
		return Database.getDataStore().createQuery( WordMap.class )
				.field( SEARCH_WORD_FIELD ).equal( searchWord ).asList();
	}

	@Override
	public WordMap getWordMapBySearchWordAndTranslation(
			final Word searchWord,
			final Word translation ) {
		return Database.getDataStore().createQuery( WordMap.class )
				.field( SEARCH_WORD_FIELD ).equal( searchWord )
				.field( TRANSLATION_FIELD ).equal( translation ).get();
	}

	@Override
	public void updateWordMap( final WordMap wordMap, final Word newSearch, final Word newTranslation ) {
		final Query<WordMap> query = Database.getDataStore().createQuery( WordMap.class )
				.field( SEARCH_WORD_FIELD ).equal( wordMap.getSearchWord() )
				.field( TRANSLATION_FIELD ).equal( wordMap.getTranslation() );

		final UpdateOperations<WordMap> updateQuery = Database.getDataStore().createUpdateOperations( WordMap.class )
				.set( SEARCH_WORD_FIELD, newSearch )
				.set( TRANSLATION_FIELD, newTranslation );

		Database.getDataStore().update( query, updateQuery );
	}

	@Override
	public void removeWordMap( final Word searchWord, final Word translation ) {
		final Query<WordMap> query = Database.getDataStore().createQuery( WordMap.class )
				.field( SEARCH_WORD_FIELD ).equal( searchWord )
				.field( TRANSLATION_FIELD ).equal( translation );

		Database.getDataStore().delete( query );
	}
}
