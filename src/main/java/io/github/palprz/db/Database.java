package io.github.palprz.db;

import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class Database {

	private static final String DB_USERNAME = "username";
	private static final String DB_PASSWORD = "password";
	private static final String DB_HOSTAME = "hostname";
	private static final int DB_POST = 12345;
	private static final String DB_NAME = "dictionary";
	private static final String MORPHIA_PACKAGE_NAME = "io.github.palprz.entity";

	private static DB db = null;
	private static MongoClient client = null;
	private static Datastore datastore = null;

	private Database() {
	}

	/**
	 * Init all database objects to avoid problem with waiting for first search.
	 */
	public static void init() {
		db = getDB();
		client = getClient();
		datastore = getDataStore();
	}

	/**
	 * Return MongoClient object.
	 * @return MongoClient
	 */
	public static MongoClient getClient() {
		if ( client == null ) {
			final MongoCredential credential = 
					MongoCredential.createCredential( DB_USERNAME, DB_NAME, DB_PASSWORD.toCharArray() );
			client = new MongoClient( new ServerAddress( DB_HOSTAME, DB_POST ), Arrays.asList( credential ) );
		}

		return client;
	}

	/**
	 * Return database from MongoClient.
	 * @return DB
	 */
	public static DB getDB() {
		if ( db == null ) {
			db = getClient().getDB( DB_NAME );
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

	/**
	 * Return Datastore object from Morphia.
	 * @return Datastore
	 */
	public static Datastore getDataStore() {
		if ( datastore == null ) {
			final Morphia morphia = new Morphia();
			morphia.mapPackage( MORPHIA_PACKAGE_NAME );
			datastore = morphia.createDatastore( getClient(), DB_NAME );
			datastore.ensureIndexes();
		}

		return datastore;
	}
}
