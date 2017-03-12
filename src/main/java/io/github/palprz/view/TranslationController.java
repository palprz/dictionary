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
		loadTransactions();

		searchedWordColumn.setCellValueFactory( cellData -> cellData.getValue().getSearchedWord() );
		foundWordColumn.setCellValueFactory( cellData -> cellData.getValue().getFoundWord() );
	}

	/**
	 * Load all transactions to the application.
	 */
	public void loadTransactions() {
		final WordMapFacade wordMapFacade = new WordMapFacadeImpl();
		final List<WordMap> maps = wordMapFacade.getAll();
		convertWordMapsToTranslations( maps );
		translationTable.setItems( translations );
	}

	//TODO it isn't the best idea and solution - I should spend more time for design this part of application.
	private void convertWordMapsToTranslations( final List<WordMap> maps ) {
		for( final WordMap map : maps ) {
			final Translation translation = new Translation( map.getSearchWord().getName(), map.getTranslation().getName() );
			translations.add( translation );
		}
	}
}
