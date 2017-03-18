package io.github.palprz.view;

import java.util.List;

import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordMapFacade;
import io.github.palprz.facade.impl.WordMapFacadeImpl;
import io.github.palprz.view.model.Translation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Main controller for Dictionary.fxml to implement logic for displaying
 * translations in application.
 */
public class TranslationController {

	@FXML
	private TableView<Translation> translationTable;

	@FXML
	private TextField searchWordTextField;

	@FXML
	private TableColumn<Translation, String> foundWordColumn;

	private static ObservableList<Translation> translations = FXCollections.observableArrayList();

	/**
	 * Init data in window.
	 */
	@FXML
	private void initialize() {
		foundWordColumn.setCellValueFactory( cellData -> cellData.getValue().getName() );
	}

	/**
	 * Action to translate search word.
	 */
	@FXML
	private void translate() {
		final WordMapFacade wordMapFacade = new WordMapFacadeImpl();
		final String searchWord = searchWordTextField.getText();
		final List<WordMap> maps;
		if ( searchWord.isEmpty() ) {
			maps = wordMapFacade.getWordMap();
		} else {
			maps = wordMapFacade.getWordMapBySearchWord( searchWord );
		}

		populateTransactionsByWordMaps( maps );
		translationTable.setItems( translations );
	}

	/**
	 * Action connected with key 'enter'.
	 */
	@FXML
	private void onEnter() {
		translate();
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
			translations.add( new Translation( "No result for search word" ) );
			return;
		}

		for ( final WordMap map : maps ) {
			translations.add( new Translation( map.getTranslation().getName() ) );
		}
	}
}
