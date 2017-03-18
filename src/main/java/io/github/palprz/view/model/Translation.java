package io.github.palprz.view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * DTO to handle necessary data for displaying in application.
 */
public class Translation {

	private StringProperty name;

	public Translation( final String translationWord ) {
		this.name = new SimpleStringProperty( translationWord );
	}

	public StringProperty getName() {
		return name;
	}

	public void setName( final String searchedWord ) {
		this.name = new SimpleStringProperty( searchedWord );
	}
}
