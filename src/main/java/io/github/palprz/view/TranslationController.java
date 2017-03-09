package io.github.palprz.view;

import io.github.palprz.entity.Word;
import io.github.palprz.entity.WordMap;
import io.github.palprz.facade.WordMapFacade;
import io.github.palprz.facade.impl.WordMapFacadeImpl;
import io.github.palprz.view.model.Translation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Main controller for Dictionary.fxml to implement logic for displaying
 * translations in application.
 */
public class TranslationController {

	@FXML
	private TableView< Translation > translationTable;

	@FXML
	private TableColumn< Translation, String > searchedWordColumn;

	@FXML
	private TableColumn< Translation, String > foundWordColumn;

	private static ObservableList< Translation > translations = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		initTransactions();
		searchedWordColumn.setCellValueFactory( cellData -> cellData.getValue().getSearchedWord() );
		foundWordColumn.setCellValueFactory( cellData -> cellData.getValue().getFoundWord() );
	}

	// TODO for now it's just a dummy method
	public void initTransactions() {
		createTestWordMap();
		
		translations.add( new Translation( "hello", "czesc" ) );
		translations.add( new Translation( "good morning", "dzien dobry" ) );
		translationTable.setItems( translations );
	}

	private void createTestWordMap() {
		WordMap wordMap = new WordMap();
		wordMap.setSearchWord( new Word( "hello" ) );
		wordMap.setTranslation( new Word( "czesc" ) );
		
		WordMapFacade wordMapFacade = new WordMapFacadeImpl();
		wordMapFacade.add( wordMap );
	}
}
