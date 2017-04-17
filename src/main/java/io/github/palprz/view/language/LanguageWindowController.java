package io.github.palprz.view.language;

import java.util.List;

import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;
import io.github.palprz.facade.LanguageFacade;
import io.github.palprz.facade.WordFacade;
import io.github.palprz.facade.impl.LanguageFacadeImpl;
import io.github.palprz.facade.impl.WordFacadeImpl;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class LanguageWindowController {

	@FXML
	private TextField addNameField;

	@FXML
	private ComboBox<Language> editOldNameCombo;

	@FXML
	private TextField editNewNameField;

	@FXML
	private ComboBox<Language> removeNameCombo;

	private static final LanguageFacade LANGUAGE_FACADE = new LanguageFacadeImpl();
	private static final WordFacade WORD_FACADE = new WordFacadeImpl();

	@FXML
	private void initialize() {
		refreshLanguageCombo();
	}

	@FXML
	private void processAdd() {
		LANGUAGE_FACADE.addLanguage( new Language( addNameField.getText() ) );
		refreshLanguageCombo();
	}

	@FXML
	private void processEdit() {
		final Language oldLang = editOldNameCombo.getSelectionModel().getSelectedItem();
		final String newName = editNewNameField.getText();
		LANGUAGE_FACADE.updateLanguage( oldLang, newName );
		refreshLanguageCombo();
	}

	@FXML
	private void processRemove() {
		final Language langToRemove = removeNameCombo.getSelectionModel().getSelectedItem();
		final List<Word> words = WORD_FACADE.getWordByLanguage( langToRemove );
		if ( words.isEmpty() ) {
			LANGUAGE_FACADE.removeLanguage( langToRemove );
			refreshLanguageCombo();
		} else {
			final StringBuilder sb = new StringBuilder( "This language is connected with '" );
			sb.append( words.size() ).append( "' word(s) and can't be remove. Please remove connected words!" );
			System.out.println( sb.toString() );
		}
	}

	/**
	 * Refresh Languages in combobox on Language Window.
	 */
	private void refreshLanguageCombo() {
		final List<Language> languages = LANGUAGE_FACADE.getAllLanguage();
		editOldNameCombo.getItems().clear();
		editOldNameCombo.getItems().addAll( languages );

		removeNameCombo.getItems().clear();
		removeNameCombo.getItems().addAll( languages );
	}
}
