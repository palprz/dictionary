package io.github.palprz.facade.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import io.github.palprz.db.Database;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordMapFacade;

public class WordMapFacadeImpl implements WordMapFacade {

	@Override
	public void add( WordMap wordMap ) {
		DBCollection collection = Database.getDB().getCollection( "wordMap" ); //TODO create another method with getting collection
		BasicDBObject document = new BasicDBObject();
		document.put( "searchWord", wordMap.getSearchWord().getName() );
		document.put( "translation", wordMap.getTranslation().getName() );
		//TODO just for testing
		collection.insert( document );
		
		DBCursor cursor = collection.find();
		while( cursor.hasNext() ) {
			DBObject test = cursor.next();
			System.out.println( test );
		}
	}

}
