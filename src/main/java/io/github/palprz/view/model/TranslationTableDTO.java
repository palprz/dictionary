package io.github.palprz.view.model;

import io.github.palprz.entity.Language;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * DTO to handle necessary data for displaying in application.
 */
public class TranslationTableDTO {

	private StringProperty name;
	private Language language;

	public TranslationTableDTO( final String translationWord, final Language languageVal ) {
		setName( translationWord );
		setLanguage( languageVal );

	}

	public StringProperty getName() {
		return name;
	}

	public void setName( final String searchedWord ) {
		this.name = new SimpleStringProperty( searchedWord );
	}

	public void setLanguage( final Language languageVal ) {
		language = languageVal;
	}

	public Language getLanguage() {
		return language;
	}
}
