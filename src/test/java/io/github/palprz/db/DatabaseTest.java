package io.github.palprz.db;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DatabaseTest {

	@Test
	public void shouldReturnNonNullClient() {
		assertNotNull( Database.getClient() );
	}

	@Test
	public void shouldReturnNonNullDataStore() {
		assertNotNull( Database.getDataStore() );
	}

	@Test
	public void shouldReturnNonNullDB() {
		assertNotNull( Database.getDB() );
	}
}
