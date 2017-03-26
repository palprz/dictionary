package io.github.palprz.view.language;

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

	@FXML
	private void processAdd() {
		System.out.println( "Add language" );
	}

	@FXML
	private void processEdit() {
		System.out.println( "Edit language" );
	}

	@FXML
	private void processRemove() {
		System.out.println( "Remove language" );
	}
}
