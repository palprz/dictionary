package io.github.palprz.view.dictionary;

import java.util.List;

import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordMapFacade;
import io.github.palprz.facade.impl.WordMapFacadeImpl;
import io.github.palprz.view.model.TranslationTableDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Main controller for Dictionary.fxml to implement logic for displaying
 * translations in application.
 */
public class DictionaryWindowController {

	@FXML
	private TableView<TranslationTableDTO> translationTable;

	@FXML
	private TextField searchWordTextField;

	@FXML
	private TextField newSearchWordTextField;

	@FXML
	private TextField newTranslationTextField;

	@FXML
	private Label messageNewTransaction;

	@FXML
	private TableColumn<TranslationTableDTO, String> foundWordColumn;

	private static ObservableList<TranslationTableDTO> translations = FXCollections.observableArrayList();

	private static final WordMapFacade WORD_MAP_FACADE = new WordMapFacadeImpl();

	/**
	 * Init data in window.
	 */
	@FXML
	private void initialize() {
		foundWordColumn.setCellValueFactory( cellData -> cellData.getValue().getName() );
	}

	/**
	 * Action connected with key 'enter'.
	 */
	@FXML
	private void onEnter() {
		translate();
	}

	/**
	 * Action to translate search word.
	 */
	@FXML
	private void translate() {
		final String searchWord = searchWordTextField.getText();
		final List<WordMap> maps;
		if ( searchWord.isEmpty() ) {
			maps = WORD_MAP_FACADE.getWordMap();
		} else {
			maps = WORD_MAP_FACADE.getWordMapBySearchWord( searchWord );
		}

		populateTransactionsByWordMaps( maps );
		translationTable.setItems( translations );
	}

	/**
	 * Action to add new translation from separate tab.
	 */
	@FXML
	private void addTranslation() {
		final Word searchWord = new Word( newSearchWordTextField.getText() );
		final Word translation = new Word( newTranslationTextField.getText() );
		final WordMap wordMap = new WordMap( searchWord, translation );
		WORD_MAP_FACADE.addWordMap( wordMap );
		showNewTransactionMessage();
	}

	/**
	 * Show message for user after added new translation.
	 */
	private void showNewTransactionMessage() {
		final StringBuilder sb = new StringBuilder()
				.append( "Added " ).append( newSearchWordTextField.getText() )
				.append( "-" ).append( newTranslationTextField.getText() );

		messageNewTransaction.setText( sb.toString() );
	}

	/**
	 * Convert entities from database into object which application can display
	 * on the screen.
	 *
	 * @param maps The collection with WordMap entities
	 */
	private void populateTransactionsByWordMaps( final List<WordMap> maps ) {
		translations.clear();
		if ( maps.isEmpty() ) {
			translations.add( new TranslationTableDTO( "No result for search word" ) );
			return;
		}

		for ( final WordMap map : maps ) {
			translations.add( new TranslationTableDTO( map.getTranslation().getName() ) );
		}
	}
}
