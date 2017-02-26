package io.github.palprz.view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * DTO to handle necessary data for displaying in application.
 */
public class Translation {

	private StringProperty searchedWord;
	private StringProperty foundWord;

	public Translation( final String searchedWord, final String foundWord ) {
		this.searchedWord = new SimpleStringProperty( searchedWord );
		this.foundWord = new SimpleStringProperty( foundWord );
	}

	public StringProperty getSearchedWord() {
		return searchedWord;
	}

	public void setSearchedWord( StringProperty searchedWord ) {
		this.searchedWord = searchedWord;
	}

	public StringProperty getFoundWord() {
		return foundWord;
	}

	public void setFoundWord( StringProperty foundWord ) {
		this.foundWord = foundWord;
	}
}
