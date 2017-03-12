package io.github.palprz.view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * DTO to handle necessary data for displaying in application.
 */
public class Translation {

	private StringProperty searchedWord;
	private StringProperty foundWord;

	public Translation() {
	}

	public Translation( final String searchedWord, final String foundWord ) {
		this.searchedWord = new SimpleStringProperty( searchedWord );
		this.foundWord = new SimpleStringProperty( foundWord );
	}

	public StringProperty getSearchedWord() {
		return searchedWord;
	}

	public void setSearchedWord( final String searchedWord ) {
		this.searchedWord = new SimpleStringProperty( searchedWord );
	}

	public StringProperty getFoundWord() {
		return foundWord;
	}

	public void setFoundWord( final String foundWord ) {
		this.foundWord = new SimpleStringProperty( foundWord );
	}
}
