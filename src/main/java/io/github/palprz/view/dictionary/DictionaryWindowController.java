package io.github.palprz.view.dictionary;

import java.io.IOException;
import java.util.List;

import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordMapFacade;
import io.github.palprz.facade.impl.WordMapFacadeImpl;
import io.github.palprz.view.StageBuilder;
import io.github.palprz.view.model.TranslationTableDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller for Dictionary window - main application window.
 */
public class DictionaryWindowController {

	@FXML
	private TextField searchWordField;

	@FXML
	private TableView<TranslationTableDTO> translationTable;

	@FXML
	private TableColumn<TranslationTableDTO, String> foundWordColumn;

	private static ObservableList<TranslationTableDTO> translations = FXCollections.observableArrayList();

	private static final WordMapFacade WORD_MAP_FACADE = new WordMapFacadeImpl();
	private static final StageBuilder STAGE_BUILDER = new StageBuilder();

	/**
	 * Init data in window.
	 */
	@FXML
	private void initialize() {
		foundWordColumn.setCellValueFactory( cellData -> cellData.getValue().getName() );
	}

	/**
	 * Handle new Language window.
	 * @throws IOException
	 */
	@FXML
	private void processLanguageWindow() throws IOException {
		STAGE_BUILDER.createLanguage();
	}

	/**
	 * Handle 'Add' option from context menu on table.
	 * @throws IOException
	 */
	@FXML
	private void processAddContextMenu() throws IOException {
		System.out.println( "adding translation" );
		STAGE_BUILDER.createTranslation();
	}

	/**
	 * Handle 'Edit' option from context menu on table.
	 * @throws IOException
	 */
	@FXML
	private void processEditContextMenu() throws IOException {
		System.out.println( "editing translation" );
		STAGE_BUILDER.createTranslation();
	}

	/**
	 * Handle 'Remove' option from context menu on table.
	 * @throws IOException
	 */
	@FXML
	private void processRemoveContextMenu() throws IOException {
		System.out.println( "remove translation" );
		STAGE_BUILDER.createTranslation();
	}

	/**
	 * Action connected with key 'enter'.
	 */
	@FXML
	private void onEnter() {
		processTranslate();
	}

	/**
	 * Action to translate search word.
	 */
	@FXML
	private void processTranslate() {
		final String searchWord = searchWordField.getText();
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
