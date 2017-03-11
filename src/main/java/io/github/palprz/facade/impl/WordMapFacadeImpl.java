package io.github.palprz.facade.impl;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import io.github.palprz.db.Database;
import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordMapFacade;

public class WordMapFacadeImpl implements WordMapFacade {

	private static final String SEARCH_WORD_KEY = "searchWord";
	private static final String TRANSLATION_KEY = "translation";
	private static final String WORD_MAP_COLLECTION_KEY = "wordMap";
	
	@Override
	public void add( WordMap wordMap ) {
		BasicDBObject document = new BasicDBObject();
		document.put( SEARCH_WORD_KEY, wordMap.getSearchWord().getName() );
		document.put( TRANSLATION_KEY, wordMap.getTranslation().getName() );

		Database.getCollection( WORD_MAP_COLLECTION_KEY ).insert( document );
	}

	@Override
	public List<WordMap> getAll() {
		DBCursor cursor = Database.getCollection( WORD_MAP_COLLECTION_KEY ).find();
		
		List<WordMap> maps = new ArrayList<>();
		while( cursor.hasNext() ) {
			DBObject dbObject = cursor.next();
			WordMap map = populateWordMap( dbObject );
			maps.add( map );
		}
		
		return maps;
	}

	/**
	 * Populate WordMap entity with data from database.
	 * TODO find better way to do this. I should spend more time for reading more stuff about mapping data in MongoDB.
	 * @param dbObject The object which contain WordMap document from database.
	 * @return WordMap
	 */
	private WordMap populateWordMap( DBObject dbObject ) {
		WordMap map = new WordMap();
		Word searchWord = new Word();
		searchWord.setName( ( String ) dbObject.get( SEARCH_WORD_KEY ) );
		Word translation = new Word();
		translation.setName( ( String ) dbObject.get( TRANSLATION_KEY ) );
		map.setSearchWord( searchWord );
		map.setTranslation( translation );
		return map;
	}
}
