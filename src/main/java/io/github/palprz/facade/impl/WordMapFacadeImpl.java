package io.github.palprz.facade.impl;

import java.util.Arrays;
import java.util.List;

import org.mongodb.morphia.query.Query;

import io.github.palprz.db.Database;
import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordMapFacade;

public class WordMapFacadeImpl implements WordMapFacade {

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
		final Word word = Database.getDataStore().createQuery(
				Word.class ).field( "name" ).equal( searchWord ).get();
		return Database.getDataStore().createQuery(
				WordMap.class ).field( "searchWord" ).equal( word ).asList();
	}
}
