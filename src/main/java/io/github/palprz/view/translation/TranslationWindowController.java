package io.github.palprz.view.translation;

import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordMapFacade;
import io.github.palprz.facade.impl.WordMapFacadeImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller for Translation window - window for manage translations.
 */
public class TranslationWindowController {

	@FXML
	private TextField addSearchWordField;

	@FXML
	private TextField addTranslationField;

	@FXML
	private TextField editOldSearchWordField;

	@FXML
	private TextField editOldTranslationField;

	@FXML
	private TextField editNewSearchWordField;

	@FXML
	private TextField editNewTranslationField;

	@FXML
	private TextField removeSearchWordField;

	@FXML
	private TextField removeTranslationField;

	private static final WordMapFacade WORD_MAP_FACADE = new WordMapFacadeImpl();

	/**
	 * Action to add new translation from separate tab.
	 */
	@FXML
	private void processAdd() {
		final Word searchWord = new Word( addSearchWordField.getText() );
		final Word translation = new Word( addTranslationField.getText() );
		final WordMap wordMap = new WordMap( searchWord, translation );
		WORD_MAP_FACADE.addWordMap( wordMap );
		System.out.println( "Translation added" );
	}

	@FXML
	private void processEdit() {
		final String oldSearchWord = editOldSearchWordField.getText();
		final String oldTranslation = editOldTranslationField.getText();
		final String newSearchWord = editNewSearchWordField.getText();
		final String newTranslation = editNewTranslationField.getText();

		final WordMap oldWordMap =
				WORD_MAP_FACADE.getWordMapBySearchWordAndTranslation( oldSearchWord, oldTranslation );

		WORD_MAP_FACADE.updateWordMap( oldWordMap, newSearchWord, newTranslation );
		System.out.println( "Translation edited" );
	}

	@FXML
	private void processRemove() {
		final String searchWord = removeSearchWordField.getText();
		final String translation = removeTranslationField.getText();
		WORD_MAP_FACADE.removeWordMap( searchWord, translation );
		System.out.println( "Translation removed" );
	}
}
