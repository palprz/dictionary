package io.github.palprz.view.language;

import java.util.List;

import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;
import io.github.palprz.facade.LanguageFacade;
import io.github.palprz.facade.WordFacade;
import io.github.palprz.facade.impl.LanguageFacadeImpl;
import io.github.palprz.facade.impl.WordFacadeImpl;
import io.github.palprz.resource.Constant;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

	@FXML
	private Label status;

	@FXML
	private Button editButton;

	@FXML
	private Button removeButton;

	private static final LanguageFacade LANGUAGE_FACADE = new LanguageFacadeImpl();
	private static final WordFacade WORD_FACADE = new WordFacadeImpl();

	@FXML
	private void initialize() {
		refreshLanguageCombo();
	}

	/**
	 * Action to add new language.
	 */
	@FXML
	private void processAdd() {
		final String name = addNameField.getText();
		if ( name.isEmpty() ) {
			setStatus( Constant.LANGUAGE_ADD_EMPTY_LANGUAGE_WARNING_MSG );
			return;
		}

		final Language foundLanguage = LANGUAGE_FACADE.getLanguageByName( name );
		if ( foundLanguage != null ) {
			setStatus( String.format( Constant.LANGUAGE_ADD_LANGUAGE_EXISTS_WARNING_MSG, name ) );
			return;
		}

		LANGUAGE_FACADE.addLanguage( new Language( addNameField.getText() ) );
		refreshLanguageCombo();
		setStatus( String.format( Constant.LANGUAGE_ADD_SUCCESS_MSG, addNameField.getText() ) );
	}

	/**
	 * Action to edit existing language.
	 */
	@FXML
	private void processEdit() {
		final Language oldLang = editOldNameCombo.getSelectionModel().getSelectedItem();
		final String newName = editNewNameField.getText();
		if ( newName.isEmpty() ) {
			setStatus( Constant.LANGUAGE_EDIT_EMPTY_LANGUAGE_WARNING_MSG );
			return;
		}

		LANGUAGE_FACADE.updateLanguage( oldLang, newName );
		refreshLanguageCombo();
		setStatus( String.format( Constant.LANGUAGE_EDIT_SUCCESS_MSG, oldLang.getName(), newName ) );
	}

	/**
	 * Action to remove existing language.
	 */
	@FXML
	private void processRemove() {
		final Language langToRemove = removeNameCombo.getSelectionModel().getSelectedItem();
		final List<Word> words = WORD_FACADE.getWordByLanguage( langToRemove );
		if ( words.isEmpty() ) {
			LANGUAGE_FACADE.removeLanguage( langToRemove );
			refreshLanguageCombo();
			setStatus( String.format( Constant.LANGUAGE_REMOVE_SUCCESS_MSG,  langToRemove.getName() ) );
		} else {
			setStatus( String.format( Constant.LANGUAGE_CONNECTED_LANGUAGE_MSG,  words.size() ) );
		}
	}

	/**
	 * Refresh Languages in combobox on Language Window.
	 */
	private void refreshLanguageCombo() {
		final List<Language> languages = LANGUAGE_FACADE.getAllLanguage();
		editOldNameCombo.getItems().clear();
		editOldNameCombo.getItems().addAll( languages );
		editOldNameCombo.getSelectionModel().select( 0 );

		removeNameCombo.getItems().clear();
		removeNameCombo.getItems().addAll( languages );
		removeNameCombo.getSelectionModel().select( 0 );

		// it should be impossible to edit/remove language if database hasn't got languages at all
		final boolean isDisable = languages.isEmpty();
		editButton.setDisable( isDisable );
		removeButton.setDisable( isDisable );
	}

	/**
	 * Set message in status bar.
	 *
	 * @param msg The message to display
	 */
	private void setStatus( final String msg ) {
		status.setText( msg );
	}
}
