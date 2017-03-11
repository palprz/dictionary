package io.github.palprz.db;

import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class Database {

	private static final String DB_USERNAME = "username";
	private static final String DB_PASSWORD = "password";
	private static final String DB_HOSTAME = "hostname";
	private static final int DB_POST = 12345;
	private static final String DB_NAME = "dictionary";

	private static DB db = null;
	
	private Database() {
	}
	
	/**
	 * Return database from MongoClient.
	 * Current local database is without password, so it doesn't check auth.
	 * @return DB 
	 */
	public static DB getDB() {
		if ( db == null ) {
			MongoCredential credential = MongoCredential.createCredential( DB_USERNAME, DB_NAME, DB_PASSWORD.toCharArray() );
			//FIXME add MongoClient.close() during closing application
			Mongo mongo = new MongoClient( new ServerAddress( DB_HOSTAME, DB_POST ), Arrays.asList( credential ) );
			db = mongo.getDB( DB_NAME );
		}

		return db;
	}
	
	/**
	 * @param collectionName the name of collection to get from database 
	 * @return DBCollection
	 */
	public static DBCollection getCollection( final String collectionName )
	{
		return getDB().getCollection( collectionName );
	}
}
