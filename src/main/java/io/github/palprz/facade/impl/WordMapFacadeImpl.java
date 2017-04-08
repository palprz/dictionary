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
	public List<WordMap> getWordMap() {
		final Query< WordMap > query = Database.getDataStore().createQuery( WordMap.class );
		return query.asList();
	}

	@Override
	public List<WordMap> getWordMapBySearchWord( final String searchWord ) {
		final Word word = WORD_FACADE.getWordByNameAndLanguage( searchWord, null ); //TODO
		return Database.getDataStore().createQuery( WordMap.class )
				.field( SEARCH_WORD_FIELD ).equal( word ).asList();
	}

	@Override
	public WordMap getWordMapBySearchWordAndTranslation( final String searchWordVal, final String translationVal ) {
		final Word searchWord = Database.getDataStore().createQuery( Word.class )
				.field( NAME_FIELD ).equal( searchWordVal ).get();

		final Word translation = Database.getDataStore().createQuery( Word.class )
				.field( NAME_FIELD ).equal( translationVal ).get();

		return Database.getDataStore().createQuery( WordMap.class )
				.field( SEARCH_WORD_FIELD ).equal( searchWord )
				.field( TRANSLATION_FIELD ).equal( translation ).get();
	}

	@Override
	public void updateWordMap( final WordMap wordMap, final String newSearchVal, final String newTranslationVal ) {
		final Word newSearchWord = WORD_FACADE.getWordByNameAndLanguage( newSearchVal, null ); //TODO missing language
		if ( newSearchWord == null ) {
//			WORD_FACADE.addWord( newSearchVal, null ); //TODO missing language
		}

		final Word newTranslationWord = WORD_FACADE.getWordByNameAndLanguage( newTranslationVal, null ); //TODO missing language
		if ( newTranslationWord == null ) {
//			WORD_FACADE.addWord( newTranslationVal, null ); //TODO missing language
		}

		final Query<WordMap> query = Database.getDataStore().createQuery( WordMap.class )
				.field( SEARCH_WORD_FIELD ).equal( wordMap.getSearchWord() )
				.field( TRANSLATION_FIELD ).equal( wordMap.getTranslation() );

		final UpdateOperations<WordMap> updateQuery = Database.getDataStore().createUpdateOperations( WordMap.class )
				.set( SEARCH_WORD_FIELD, newSearchWord )
				.set( TRANSLATION_FIELD, newTranslationWord );

		Database.getDataStore().update( query, updateQuery );
	}

	@Override
	public void removeWordMap( final String searchWordVal, final String translationVal ) {
		final Word search = WORD_FACADE.getWordByNameAndLanguage( searchWordVal, null ); //TODO missing language
		final Word translation = WORD_FACADE.getWordByNameAndLanguage( translationVal, null ); //TODO missing language

		final Query<WordMap> query = Database.getDataStore().createQuery( WordMap.class )
				.field( SEARCH_WORD_FIELD ).equal( search )
				.field( TRANSLATION_FIELD ).equal( translation );

		Database.getDataStore().delete( query );
	}
}
