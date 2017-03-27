package io.github.palprz.view.language;

import io.github.palprz.facade.LanguageFacade;
import io.github.palprz.facade.impl.LanguageFacadeImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LanguageWindowController {

	@FXML
	private TextField addNameField;

	@FXML
	private TextField editOldNameField;

	@FXML
	private TextField editNewNameField;

	@FXML
	private TextField removeNameField;

	private static final LanguageFacade LANGUAGE_FACADE = new LanguageFacadeImpl();

	@FXML
	private void processAdd() {
		LANGUAGE_FACADE.addLanguage( addNameField.getText() );
		System.out.println( "Language added" );
	}

	@FXML
	private void processEdit() {
		System.out.println( "Language edited" );
		LANGUAGE_FACADE.updateLanguage( editOldNameField.getText(), editNewNameField.getText() );
	}

	@FXML
	private void processRemove() {
		System.out.println( "Language removed" );
	}
}
