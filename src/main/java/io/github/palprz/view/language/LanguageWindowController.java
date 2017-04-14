package io.github.palprz.view.language;

import java.util.List;

import io.github.palprz.entity.Language;
import io.github.palprz.facade.LanguageFacade;
import io.github.palprz.facade.impl.LanguageFacadeImpl;
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
		final String oldName = editOldNameCombo.getSelectionModel().getSelectedItem().getName();
		final String newName = editNewNameField.getText();
		LANGUAGE_FACADE.updateLanguage( oldName, newName );
		refreshLanguageCombo();
	}

	@FXML
	private void processRemove() {
		LANGUAGE_FACADE.removeLanguage( removeNameCombo.getSelectionModel().getSelectedItem().getName() );
		refreshLanguageCombo();
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
