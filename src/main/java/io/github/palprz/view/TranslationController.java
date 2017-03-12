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
	private TableView<Translation> translationTable;

	@FXML
	private TableColumn<Translation, String> searchedWordColumn;

	@FXML
	private TableColumn<Translation, String> foundWordColumn;

	private static ObservableList<Translation> translations = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		loadTranslations();

		searchedWordColumn.setCellValueFactory( cellData -> cellData.getValue().getSearchedWord() );
		foundWordColumn.setCellValueFactory( cellData -> cellData.getValue().getFoundWord() );
	}

	/**
	 * Load all translations from database into application.
	 */
	public void loadTranslations() {
		final WordMapFacade wordMapFacade = new WordMapFacadeImpl();
		final List<WordMap> maps = wordMapFacade.getAll();
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
		for ( final WordMap map : maps ) {
			final Translation translation = new Translation();
			if ( map.getSearchWord() != null ) {
				translation.setSearchedWord( map.getSearchWord().getName() );
			}

			if ( map.getTranslation() != null ) {
				translation.setFoundWord( map.getTranslation().getName() );
			}

			translations.add( translation );
		}
	}
}
