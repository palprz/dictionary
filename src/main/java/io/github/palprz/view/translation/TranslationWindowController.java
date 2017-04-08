package io.github.palprz.view.translation;

import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordFacade;
import io.github.palprz.facade.WordMapFacade;
import io.github.palprz.facade.impl.LanguageFacadeImpl;
import io.github.palprz.facade.impl.WordFacadeImpl;
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
	private TextField addSearchWordLangField;

	@FXML
	private TextField addTranslationField;

	@FXML
	private TextField addTranslationLangField;


	@FXML
	private TextField editOldSearchWordField;

	@FXML
	private TextField editOldSearchWordLangField;

	@FXML
	private TextField editOldTranslationField;

	@FXML
	private TextField editOldTranslationLangField;

	@FXML
	private TextField editNewSearchWordField;

	@FXML
	private TextField editNewSearchWordLangField;

	@FXML
	private TextField editNewTranslationField;

	@FXML
	private TextField editNewTranslationLangField;


	@FXML
	private TextField removeSearchWordField;

	@FXML
	private TextField removeSearchWordLangField;

	@FXML
	private TextField removeTranslationField;

	@FXML
	private TextField removeTranslationLangField;

	private static final WordMapFacade WORD_MAP_FACADE = new WordMapFacadeImpl();
	private static final LanguageFacadeImpl LANGUAGE_FACADE = new LanguageFacadeImpl();
	private static final WordFacade WORD_FACADE = new WordFacadeImpl();

	/**
	 * Action to add new translation from separate tab.
	 */
	@FXML
	private void processAdd() {
		Language langSearchWord = LANGUAGE_FACADE.getLanguageByName( addSearchWordLangField.getText() );
		if ( langSearchWord == null ) {
			langSearchWord = new Language();
			langSearchWord.setName( addSearchWordLangField.getText() );
			LANGUAGE_FACADE.addLanguage( langSearchWord );
		}

		Language langTranslation = LANGUAGE_FACADE.getLanguageByName( addTranslationLangField.getText() );
		if ( langTranslation == null ) {
			langTranslation = new Language();
			langTranslation.setName( addTranslationLangField.getText() );
			LANGUAGE_FACADE.addLanguage( langTranslation );
		}

		Word searchWord = WORD_FACADE.getWordByNameAndLanguage( addSearchWordField.getText(), langSearchWord );
		if ( searchWord == null ) {
			searchWord = new Word();
			searchWord.setName( addSearchWordField.getText() );
			searchWord.setLanguage( langSearchWord );
			WORD_FACADE.addWord( searchWord );
		}

		Word translation = WORD_FACADE.getWordByNameAndLanguage( addTranslationField.getText(), langTranslation );
		if ( translation == null ) {
			translation = new Word();
			translation.setName( addTranslationField.getText() );
			translation.setLanguage( langTranslation );
			WORD_FACADE.addWord( translation );
		}

		//TODO check if WordMap is already in the database (avoid duplicates)
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
