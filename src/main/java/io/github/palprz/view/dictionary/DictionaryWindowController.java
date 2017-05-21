package io.github.palprz.view.dictionary;

import java.io.IOException;
import java.util.List;

import io.github.palprz.entity.Language;
import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.entity.enums.Action;
import io.github.palprz.facade.LanguageFacade;
import io.github.palprz.facade.WordFacade;
import io.github.palprz.facade.WordMapFacade;
import io.github.palprz.facade.impl.LanguageFacadeImpl;
import io.github.palprz.facade.impl.WordFacadeImpl;
import io.github.palprz.facade.impl.WordMapFacadeImpl;
import io.github.palprz.resource.Constant;
import io.github.palprz.view.StageBuilder;
import io.github.palprz.view.model.TranslationTableDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller for Dictionary window - main application window.
 */
public class DictionaryWindowController {

	@FXML
	public ComboBox<Language> searchWordLangCombo;

	@FXML
	public TextField searchWordField;

	@FXML
	public TableView<TranslationTableDTO> translationTable;

	@FXML
	private TableColumn<TranslationTableDTO, String> foundWordColumn;

	@FXML
	private CheckMenuItem isCheckedReset;

	@FXML
	private Label status;

	@FXML
	private Button translateButton;

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
	 * Refresh window.
	 */
	@FXML
	private void processRefresh() {
		refreshLanguageCombo();
	}

	/**
	 * Handle new Translation window.
	 * @throws IOException
	 */
	@FXML
	private void processTranslationWindow() throws IOException {
		STAGE_BUILDER.createTranslation( this, Action.OPEN );
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
		if ( isCheckedReset.isSelected() ) {
			WORD_MAP_FACADE.removeAllWordMaps();
			WORD_FACADE.removeAllWords();
			LANGUAGE_FACADE.removeAllLanguages();
			isCheckedReset.setSelected( false );
			refreshLanguageCombo();
			setStatus( Constant.DICTIONARY_RESET_DB_SUCCESS_MSG );
		} else {
			setStatus( Constant.DICTIONARY_AGREE_RESET_DB_MSG );
		}
	}

	/**
	 * Handle 'Add' option from context menu on table.
	 * @throws IOException
	 */
	@FXML
	private void processAddContextMenu() throws IOException {
		STAGE_BUILDER.createTranslation( this, Action.ADD );
	}

	/**
	 * Handle 'Edit' option from context menu on table.
	 * @throws IOException
	 */
	@FXML
	private void processEditContextMenu() throws IOException {
		STAGE_BUILDER.createTranslation( this, Action.EDIT );
	}

	/**
	 * Handle 'Remove' option from context menu on table.
	 * @throws IOException
	 */
	@FXML
	private void processRemoveContextMenu() throws IOException {
		STAGE_BUILDER.createTranslation( this, Action.REMOVE );
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
		if ( translateButton.isDisable() ) {
			setStatus( Constant.DICTIONARY_NO_LANGUAGES_WARNING_MSG );
			return;
		}

		final Language language = searchWordLangCombo.getSelectionModel().getSelectedItem();
		final Word word = WORD_FACADE.getWordByNameAndLanguage( searchWordField.getText(), language );
		final List<WordMap> maps = WORD_MAP_FACADE.getWordMapByWord( word );

		populateTransactionsByWordMaps( word, maps );

		if ( word == null ) {
			setStatus( String.format( Constant.DICTIONARY_NO_WORD_TRANSLATE_MSG, searchWordField.getText() ) );
		} else {
			setStatus( String.format( Constant.DICTIONARY_TRANSLATE_SUCCESS_MSG, searchWordField.getText() ) );
		}
	}

	/**
	 * Convert entities from database into object which application can display
	 * on the screen.
	 *
	 * @param word The word which is a search word
	 * @param maps The collection with WordMap entities
	 */
	private void populateTransactionsByWordMaps( final Word word, final List<WordMap> maps ) {
		translations.clear();
		if ( maps.isEmpty() ) {
			translations.add( new TranslationTableDTO( "No result for search word" , null ) );
		} else {
			for ( final WordMap map : maps ) {
				if ( map.getSearchWord().equals( word ) ) {
					translations.add( new TranslationTableDTO(
							map.getTranslation().getName(),
							map.getTranslation().getLanguage() ) );
				} else {
					translations.add( new TranslationTableDTO(
							map.getSearchWord().getName(),
							map.getSearchWord().getLanguage() ) );
				}
			}
		}

		translationTable.setItems( translations );
	}

	/**
	 * Refresh Languages in combobox on Dictionary Window.
	 */
	private void refreshLanguageCombo() {
		final List<Language> languages = LANGUAGE_FACADE.getAllLanguage();
		searchWordLangCombo.getItems().clear();
		searchWordLangCombo.getItems().addAll( languages );
		searchWordLangCombo.getSelectionModel().select( 0 );
		translateButton.setDisable( languages.size() < 2 );
	}

	/**
	 * Action to swap words.
	 */
	@FXML
	private void processSwapWords() {
		final TranslationTableDTO selectedItem = translationTable.getSelectionModel().getSelectedItem();
		final String name = getSwapName( selectedItem );
		searchWordField.setText( name );
		final Language language = getSwapLanguage( selectedItem );
		searchWordLangCombo.getSelectionModel().select( language );
		processTranslate();
	}

	/**
	 * Get name for swap in search word text field. Cases:
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

	/**
	 * Get language for swap in combo box with search language. Cases:
	 * - from selected item in the table
	 * - from first item in the table if selected item is not specify
	 * - the same language what is current if table is empty
	 *
	 * @param selectedItem The selected item in the table.
	 * @return Language the language to set in combobox
	 */
	private Language getSwapLanguage( final TranslationTableDTO selectedItem ) {
		Language language;
		if ( selectedItem == null ) {
			if ( translationTable.getItems().isEmpty() ) {
				language = searchWordLangCombo.getSelectionModel().getSelectedItem();
			} else {
				language = translationTable.getItems().get( 0 ).getLanguage();
			}
		} else {
			language = selectedItem.getLanguage();
		}

		return language;
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
