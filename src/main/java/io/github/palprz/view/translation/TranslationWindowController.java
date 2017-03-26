package io.github.palprz.view.translation;

import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordMapFacade;
import io.github.palprz.facade.impl.WordMapFacadeImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for Translation window - window for manage translations.
 */
public class TranslationWindowController {

	@FXML
	private TextField addSearchWordTextField;

	@FXML
	private TextField addTranslationTextField;

	@FXML
	private TextField editOldSearchWordTextField;

	@FXML
	private TextField editOldTranslationTextField;

	@FXML
	private TextField editNewSearchWordTextField;

	@FXML
	private TextField editNewTranslationTextField;

	@FXML
	private Label messageNewTransaction;

	private static final WordMapFacade WORD_MAP_FACADE = new WordMapFacadeImpl();

	/**
	 * Action to add new translation from separate tab.
	 */
	@FXML
	private void processAdd() {
		final Word searchWord = new Word( addSearchWordTextField.getText() );
		final Word translation = new Word( addTranslationTextField.getText() );
		final WordMap wordMap = new WordMap( searchWord, translation );
		WORD_MAP_FACADE.addWordMap( wordMap );
	}

	@FXML
	private void processEdit() {
		final String oldSearchWord = editOldSearchWordTextField.getText();
		final String oldTranslation = editOldTranslationTextField.getText();
		final String newSearchWord = editNewSearchWordTextField.getText();
		final String newTranslation = editNewTranslationTextField.getText();

		final WordMap oldWordMap =
				WORD_MAP_FACADE.getWordMapBySearchWordAndTranslation( oldSearchWord, oldTranslation );

		WORD_MAP_FACADE.updateWordMap( oldWordMap, newSearchWord, newTranslation );
	}

}
