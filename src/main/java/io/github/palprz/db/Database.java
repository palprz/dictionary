package io.github.palprz.db;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Database {

	private static final Logger LOGGER = Logger.getLogger( Database.class.getName() );

	private static final String DB_HOSTAME = "localhost";
	private static final int DB_POST = 27017;
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
			try {
				MongoClient mongo = new MongoClient( DB_HOSTAME, DB_POST );
				db = mongo.getDB( DB_NAME );
			} catch ( UnknownHostException e ) {
				LOGGER.log( Level.WARNING, "Exception during getting connection with " + DB_HOSTAME + ":" + DB_POST, e );
			}			
		}

		return db;
	}
}
