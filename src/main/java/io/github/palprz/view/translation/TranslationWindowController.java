package io.github.palprz.view.translation;

import java.util.Arrays;
import java.util.List;

import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordFacade;
import io.github.palprz.facade.WordMapFacade;
import io.github.palprz.facade.impl.LanguageFacadeImpl;
import io.github.palprz.facade.impl.WordFacadeImpl;
import io.github.palprz.facade.impl.WordMapFacadeImpl;
import io.github.palprz.resource.Constant;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for Translation window - window for manage translations.
 */
public class TranslationWindowController {

	@FXML
	private TextField addSearchWordField;

	@FXML
	private ComboBox<Language> addSearchWordLangCombo;

	@FXML
	private TextField addTranslationField;

	@FXML
	private ComboBox<Language> addTranslationLangCombo;


	@FXML
	private TextField editOldSearchWordField;

	@FXML
	private ComboBox<Language> editOldSearchWordLangCombo;

	@FXML
	private TextField editOldTranslationField;

	@FXML
	private ComboBox<Language> editOldTranslationLangCombo;

	@FXML
	private TextField editNewSearchWordField;

	@FXML
	private ComboBox<Language> editNewSearchWordLangCombo;

	@FXML
	private TextField editNewTranslationField;

	@FXML
	private ComboBox<Language> editNewTranslationLangCombo;


	@FXML
	private TextField removeSearchWordField;

	@FXML
	private ComboBox<Language> removeSearchWordLangCombo;

	@FXML
	private TextField removeTranslationField;

	@FXML
	private ComboBox<Language> removeTranslationLangCombo;

	@FXML
	private Label status;

	private static final WordMapFacade WORD_MAP_FACADE = new WordMapFacadeImpl();
	private static final LanguageFacadeImpl LANGUAGE_FACADE = new LanguageFacadeImpl();
	private static final WordFacade WORD_FACADE = new WordFacadeImpl();

	@FXML
	private void initialize() {
		refreshLanguageCombo();
	}

	/**
	 * Action to add new translation from separate tab.
	 */
	@FXML
	private void processAdd() {
		final Language langSearchWord = addSearchWordLangCombo.getSelectionModel().getSelectedItem();
		final Language langTranslation = addTranslationLangCombo.getSelectionModel().getSelectedItem();

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
		final StringBuilder sb = new StringBuilder()
				.append( addSearchWordField.getText() )
				.append( "-" )
				.append( addTranslationField.getText() );
		setStatus( String.format( Constant.TRANSLATION_ADD_SUCCESS_MSG, sb.toString() ) );
	}

	@FXML
	private void processEdit() {
		final String oldSearchWordName = editOldSearchWordField.getText();
		final Language oldSearchWordLang = editOldSearchWordLangCombo.getSelectionModel().getSelectedItem();
		final String oldTranslationName = editOldTranslationField.getText();
		final Language oldTranslationLang = editOldTranslationLangCombo.getSelectionModel().getSelectedItem();

		final Word oldSearchWord = WORD_FACADE.getWordByNameAndLanguage( oldSearchWordName, oldSearchWordLang );
		final Word oldTranslation = WORD_FACADE.getWordByNameAndLanguage( oldTranslationName, oldTranslationLang );

		final WordMap oldWordMap =
				WORD_MAP_FACADE.getWordMapBySearchWordAndTranslation( oldSearchWord, oldTranslation );

		final String newSearchWordName = editNewSearchWordField.getText();
		final Language newSearchWordLang = editNewSearchWordLangCombo.getSelectionModel().getSelectedItem();

		Word newSearchWord = WORD_FACADE.getWordByNameAndLanguage( newSearchWordName, newSearchWordLang );
		if ( newSearchWord == null ) {
			newSearchWord = new Word();
			newSearchWord.setLanguage( newSearchWordLang );
			newSearchWord.setName( newSearchWordName );
			WORD_FACADE.addWord( newSearchWord );
		}

		final String newTranslationName = editNewTranslationField.getText();
		final Language newTranslationLang = editNewTranslationLangCombo.getSelectionModel().getSelectedItem();

		Word newTranslation = WORD_FACADE.getWordByNameAndLanguage( newTranslationName, newTranslationLang );
		if ( newTranslation == null ) {
			newTranslation = new Word();
			newTranslation.setLanguage( newTranslationLang );
			newTranslation.setName( newTranslationName );
			WORD_FACADE.addWord( newTranslation );
		}

		WORD_MAP_FACADE.updateWordMap( oldWordMap, newSearchWord, newTranslation );
		final StringBuilder oldSb = new StringBuilder()
				.append( oldSearchWordName )
				.append( "-" )
				.append( oldTranslationName );

		final StringBuilder newSb = new StringBuilder()
				.append( newSearchWordName )
				.append( "-" )
				.append( newTranslationName );
		setStatus( String.format( Constant.TRANSLATION_EDIT_SUCCESS_MSG, oldSb.toString(), newSb.toString() ) );
	}

	@FXML
	private void processRemove() {
		final Language searchWordLang = removeSearchWordLangCombo.getSelectionModel().getSelectedItem();
		final Word searchWord =
				WORD_FACADE.getWordByNameAndLanguage( removeSearchWordField.getText(), searchWordLang );

		final Language translationLang = removeTranslationLangCombo.getSelectionModel().getSelectedItem();
		final Word translation =
				WORD_FACADE.getWordByNameAndLanguage( removeTranslationField.getText(), translationLang );

		WORD_MAP_FACADE.removeWordMap( searchWord, translation );
		final List<WordMap> searchWordMaps = WORD_MAP_FACADE.getWordMapBySearchWord( searchWord );
		if ( searchWordMaps.isEmpty() ) {
			WORD_FACADE.removeWord( searchWord );
		}

		final List<WordMap> translationMaps = WORD_MAP_FACADE.getWordMapBySearchWord( translation );
		if ( translationMaps.isEmpty() ) {
			WORD_FACADE.removeWord( translation );
			final StringBuilder sb = new StringBuilder()
					.append( removeSearchWordField.getText() )
					.append( "-" )
					.append( removeTranslationField.getText() );
			setStatus( String.format( Constant.TRANSLATION_REMOVE_SUCCESS_MSG, sb.toString() ) );
		}
	}

	private void refreshLanguageCombo() {
		final List<Language> languages = LANGUAGE_FACADE.getAllLanguage();
		final List<ComboBox> comboxes = Arrays.asList(
				addSearchWordLangCombo, addTranslationLangCombo,
				editOldSearchWordLangCombo, editOldTranslationLangCombo,
				editNewSearchWordLangCombo, editNewTranslationLangCombo,
				removeSearchWordLangCombo, removeTranslationLangCombo
			);

		for ( final ComboBox<Language> combo : comboxes ) {
			combo.getItems().clear();
			combo.getItems().setAll( languages );
		}
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
