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
		//TODO languages should be in dropdown list, so it will be impossible to add anything what is not in the database
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
	}

	@FXML
	private void processEdit() {
		final String oldSearchWordName = editOldSearchWordField.getText();
		final Language oldSearchWordLang = LANGUAGE_FACADE.getLanguageByName( editOldSearchWordLangField.getText() );
		final String oldTranslationName = editOldTranslationField.getText();
		final Language oldTranslationLang = LANGUAGE_FACADE.getLanguageByName( editOldTranslationLangField.getText() );

		final Word oldSearchWord = WORD_FACADE.getWordByNameAndLanguage( oldSearchWordName, oldSearchWordLang );
		final Word oldTranslation = WORD_FACADE.getWordByNameAndLanguage( oldTranslationName, oldTranslationLang );

		final WordMap oldWordMap =
				WORD_MAP_FACADE.getWordMapBySearchWordAndTranslation( oldSearchWord, oldTranslation );

		final String newSearchWordName = editNewSearchWordField.getText();
		final Language newSearchWordLang = LANGUAGE_FACADE.getLanguageByName( editNewSearchWordLangField.getText() );
		if ( newSearchWordLang == null ) {
			LANGUAGE_FACADE.addLanguage( newSearchWordLang );
		}

		final String newTranslationName = editNewTranslationField.getText();
		final Language newTranslationLang = LANGUAGE_FACADE.getLanguageByName( editNewTranslationLangField.getText() );
		if ( newTranslationLang == null ) {
			LANGUAGE_FACADE.addLanguage( newTranslationLang );
		}

		final Word newSearchWord = WORD_FACADE.getWordByNameAndLanguage( newSearchWordName, newSearchWordLang );
		if ( newSearchWord == null ) {
			WORD_FACADE.addWord( newSearchWord );
		}

		final Word newTranslation = WORD_FACADE.getWordByNameAndLanguage( newTranslationName, newTranslationLang );
		if ( newTranslation == null ) {
			WORD_FACADE.addWord( newTranslation );
		}

		WORD_MAP_FACADE.updateWordMap( oldWordMap, newSearchWord, newTranslation );
	}

	@FXML
	private void processRemove() {
		final Language searchWordLang = LANGUAGE_FACADE.getLanguageByName( removeSearchWordLangField.getText() );
		final Word searchWord =
				WORD_FACADE.getWordByNameAndLanguage( removeSearchWordField.getText(), searchWordLang );

		final Language translationLang = LANGUAGE_FACADE.getLanguageByName( removeTranslationLangField.getText() );
		final Word translation =
				WORD_FACADE.getWordByNameAndLanguage( removeTranslationField.getText(), translationLang );

		WORD_MAP_FACADE.removeWordMap( searchWord, translation );
	}
}
