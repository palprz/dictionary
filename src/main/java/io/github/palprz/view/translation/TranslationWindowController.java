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
import javafx.scene.control.Button;
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

	@FXML
	private Button addButton;

	@FXML
	private Button editButton;

	@FXML
	private Button removeButton;

	private static final WordMapFacade WORD_MAP_FACADE = new WordMapFacadeImpl();
	private static final LanguageFacadeImpl LANGUAGE_FACADE = new LanguageFacadeImpl();
	private static final WordFacade WORD_FACADE = new WordFacadeImpl();

	@FXML
	private void initialize() {
		refreshLanguageCombo();
	}

	/**
	 * Action to add new translation.
	 */
	@FXML
	private void processAdd() {
		final String searchWordName = addSearchWordField.getText();
		if ( searchWordName.isEmpty() ) {
			setStatus( Constant.TRANSLATION_ADD_EMPTY_SEARCH_WORD_WARNING_MSG );
			return;
		}

		final String translationName = addTranslationField.getText();
		if ( translationName.isEmpty() ) {
			setStatus( Constant.TRANSLATION_ADD_EMPTY_TRANSLATION_WARNING_MSH );
			return;
		}

		final Language langSearchWord = addSearchWordLangCombo.getSelectionModel().getSelectedItem();
		final Language langTranslation = addTranslationLangCombo.getSelectionModel().getSelectedItem();
		if ( langSearchWord.equals( langTranslation ) ) {
			final StringBuilder sb = new StringBuilder()
					.append( searchWordName ).append( "-" ).append( translationName );
			setStatus( String.format( Constant.TRANSLATION_ADD_SAME_LANGUAGE_WARNING_MSG, sb.toString() ) );
			return;
		}

		Word searchWord = WORD_FACADE.getWordByNameAndLanguage( searchWordName, langSearchWord );
		if ( searchWord == null ) {
			searchWord = new Word();
			searchWord.setName( addSearchWordField.getText() );
			searchWord.setLanguage( langSearchWord );
			WORD_FACADE.addWord( searchWord );
		}

		Word translation = WORD_FACADE.getWordByNameAndLanguage( translationName, langTranslation );
		if ( translation == null ) {
			translation = new Word();
			translation.setName( translationName );
			translation.setLanguage( langTranslation );
			WORD_FACADE.addWord( translation );
		}

		WordMap wordMap = WORD_MAP_FACADE.getWordMapBySearchWordAndTranslation( searchWord, translation );
		if ( wordMap == null ) {
			wordMap = new WordMap( searchWord, translation );
			WORD_MAP_FACADE.addWordMap( wordMap );
		}

		final StringBuilder sb = new StringBuilder()
				.append( searchWordName ).append( "-" ).append( translationName );
		setStatus( String.format( Constant.TRANSLATION_ADD_SUCCESS_MSG, sb.toString() ) );
	}

	/**
	 * Action to edit existing translation.
	 */
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
		if ( newSearchWordName.isEmpty() ) {
			setStatus( Constant.TRANSLATION_EDIT_EMPTY_SEARCH_WORD_WARNING_MSG );
			return;
		}

		final String newTranslationName = editNewTranslationField.getText();
		if ( newTranslationName.isEmpty() ) {
			setStatus( Constant.TRANSLATION_EDIT_EMPTY_TRANSLATION_WARNING_MSG );
			return;
		}

		final Language newSearchWordLang = editNewSearchWordLangCombo.getSelectionModel().getSelectedItem();
		final Language newTranslationLang = editNewTranslationLangCombo.getSelectionModel().getSelectedItem();
		if ( newSearchWordLang.equals( newTranslationLang ) ) {
			final StringBuilder sb = new StringBuilder()
					.append( newSearchWordName ).append( "-" ).append( newTranslationName );
			setStatus( String.format( Constant.TRANSLATION_EDIT_SAME_LANGUAGE_WARNING_MSG, sb.toString() ) );
			return;
		}

		Word newSearchWord = WORD_FACADE.getWordByNameAndLanguage( newSearchWordName, newSearchWordLang );
		if ( newSearchWord == null ) {
			newSearchWord = new Word();
			newSearchWord.setLanguage( newSearchWordLang );
			newSearchWord.setName( newSearchWordName );
			WORD_FACADE.addWord( newSearchWord );
		}


		Word newTranslation = WORD_FACADE.getWordByNameAndLanguage( newTranslationName, newTranslationLang );
		if ( newTranslation == null ) {
			newTranslation = new Word();
			newTranslation.setLanguage( newTranslationLang );
			newTranslation.setName( newTranslationName );
			WORD_FACADE.addWord( newTranslation );
		}

		WORD_MAP_FACADE.updateWordMap( oldWordMap, newSearchWord, newTranslation );
		final StringBuilder oldSb = new StringBuilder()
				.append( oldSearchWordName ).append( "-" ).append( oldTranslationName );

		final StringBuilder newSb = new StringBuilder()
				.append( newSearchWordName ).append( "-" ).append( newTranslationName );
		setStatus( String.format( Constant.TRANSLATION_EDIT_SUCCESS_MSG, oldSb.toString(), newSb.toString() ) );
	}

	/**
	 * Action to remove existing translation.
	 */
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
					.append( removeSearchWordField.getText() ).append( "-" ).append( removeTranslationField.getText() );
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
			combo.getSelectionModel().select( 0 );
		}

		/*
		 * It should be impossible to edit/remove language if
		 * database hasn't got languages at all or there's only 1 language
		 */
		final boolean isDisable = languages.isEmpty() || languages.size() == 1;
		addButton.setDisable( isDisable );
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
