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

	private static final WordFacadeImpl WORD_FACADE = new WordFacadeImpl();

	@Override
	public void addWordMap( final WordMap wordMap ) {
		final Word searchWord = wordMap.getSearchWord();
		final Word translation = wordMap.getTranslation();

		//TODO can I do it better?
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
		final Word word = WORD_FACADE.getWordByName( searchWord );

		return Database.getDataStore().createQuery( WordMap.class ).field( "searchWord" ).equal( word ).asList();
	}

	@Override
	public WordMap getWordMapBySearchWordAndTranslation( final String searchWordVal, final String translationVal ) {
		final Word searchWord = Database.getDataStore().createQuery(
				Word.class ).field( "name" ).equal( searchWordVal ).get();

		final Word translation = Database.getDataStore().createQuery(
				Word.class ).field( "name" ).equal( translationVal ).get();

		return Database.getDataStore().createQuery( WordMap.class )
				.field( "searchWord" ).equal( searchWord )
				.field( "translation" ).equal( translation ).get();
	}

	@Override
	public void updateWordMap( final WordMap wordMap, final String newSearchVal, final String newTranslationVal ) {

		Word newSearchWord = WORD_FACADE.getWordByName( newSearchVal );
		if ( newSearchWord == null ) {
			newSearchWord = WORD_FACADE.addWord( newSearchVal );
		}

		Word newTranslationWord = WORD_FACADE.getWordByName( newTranslationVal );
		if ( newTranslationWord == null ) {
			newTranslationWord = WORD_FACADE.addWord( newTranslationVal );
		}

		final Query<WordMap> query = Database.getDataStore().createQuery( WordMap.class )
				.field( "searchWord" ).equal( wordMap.getSearchWord() )
				.field( "translation" ).equal( wordMap.getTranslation() );

		final UpdateOperations<WordMap> updateQuery = Database.getDataStore().createUpdateOperations( WordMap.class )
				.set( "searchWord", newSearchWord )
				.set( "translation", newTranslationWord );

		Database.getDataStore().update( query, updateQuery );
	}
}
