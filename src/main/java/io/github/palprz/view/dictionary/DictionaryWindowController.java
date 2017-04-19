package io.github.palprz.view.dictionary;

import java.io.IOException;
import java.util.List;

import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.LanguageFacade;
import io.github.palprz.facade.WordFacade;
import io.github.palprz.facade.WordMapFacade;
import io.github.palprz.facade.impl.LanguageFacadeImpl;
import io.github.palprz.facade.impl.WordFacadeImpl;
import io.github.palprz.facade.impl.WordMapFacadeImpl;
import io.github.palprz.view.StageBuilder;
import io.github.palprz.view.model.TranslationTableDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller for Dictionary window - main application window.
 */
public class DictionaryWindowController {

	@FXML
	private ComboBox<Language> searchWordLangCombo;

	@FXML
	private TextField searchWordField;

	@FXML
	private TableView<TranslationTableDTO> translationTable;

	@FXML
	private TableColumn<TranslationTableDTO, String> foundWordColumn;

	@FXML
	private CheckMenuItem isCheckedReset;

	private final ObservableList<TranslationTableDTO> translations = FXCollections.observableArrayList();

	private static final WordMapFacade WORD_MAP_FACADE = new WordMapFacadeImpl();
	private static final LanguageFacade LANGUAGE_FACADE = new LanguageFacadeImpl();
	private static final WordFacade WORD_FACADE = new WordFacadeImpl();
	private static final StageBuilder STAGE_BUILDER = new StageBuilder();

	/**
	 * Init data in window.
	 */
	@FXML
	private void initialize() {
		foundWordColumn.setCellValueFactory( cellData -> cellData.getValue().getName() );
		refreshLanguageCombo();
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
	 * Handle new Info window.
	 * @throws IOException
	 */
	@FXML
	private void processInfoWindow() throws IOException {
		STAGE_BUILDER.createInfo();
	}

	/**
	 * Handle reset database.
	 */
	@FXML
	private void processResetDatabase() {
		//TODO add status which is saying something about not clicked agree for reset database
		if ( isCheckedReset.isSelected() ) {
			WORD_MAP_FACADE.removeAllWordMaps();
			WORD_FACADE.removeAllWords();
			LANGUAGE_FACADE.removeAllLanguages();
			isCheckedReset.setSelected( false );
		}
	}

	/**
	 * Handle 'Add' option from context menu on table.
	 * @throws IOException
	 */
	@FXML
	private void processAddContextMenu() throws IOException {
		STAGE_BUILDER.createTranslation();
	}

	/**
	 * Handle 'Edit' option from context menu on table.
	 * @throws IOException
	 */
	@FXML
	private void processEditContextMenu() throws IOException {
		STAGE_BUILDER.createTranslation();
	}

	/**
	 * Handle 'Remove' option from context menu on table.
	 * @throws IOException
	 */
	@FXML
	private void processRemoveContextMenu() throws IOException {
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
		final Language language = searchWordLangCombo.getSelectionModel().getSelectedItem();
		final Word word = WORD_FACADE.getWordByNameAndLanguage( searchWordField.getText(), language );
		final List<WordMap> maps = WORD_MAP_FACADE.getWordMapBySearchWord( word );

		populateTransactionsByWordMaps( maps );
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
		} else {
			for ( final WordMap map : maps ) {
				translations.add( new TranslationTableDTO( map.getTranslation().getName() ) );
			}
		}

		translationTable.setItems( translations );
	}

	/**
	 * Refresh Languages in combobox on Dictionary Window.
	 */
	private void refreshLanguageCombo() {
		searchWordLangCombo.getItems().clear();
		searchWordLangCombo.getItems().addAll( LANGUAGE_FACADE.getAllLanguage() );
	}

	/**
	 * Action to swap words.
	 */
	@FXML
	private void processSwapWords() {
		final TranslationTableDTO selectedItem = translationTable.getSelectionModel().getSelectedItem();
		final String name = getSwapName( selectedItem );
		searchWordField.setText( name );
		processTranslate();
	}

	/**
	 * Get name for swap name in search word text field. The name will be:
	 * - from selected item in the table
	 * - from first item in the table if selected item is not specify
	 * - empty String if table is empty
	 *
	 * @param selectedItem The selected item in the table.
	 * @return String with name to set in search word text field
	 */
	private String getSwapName( final TranslationTableDTO selectedItem ) {
		String selectedName;
		if ( selectedItem == null ) {
			if ( translationTable.getItems().isEmpty() ) {
				selectedName = "";
			} else {
				selectedName = translationTable.getItems().get( 0 ).getName().getValue();
			}
		} else {
			selectedName = selectedItem.getName().getValue();
		}

		return selectedName;
	}
}
