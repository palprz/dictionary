package io.github.palprz.view;

import io.github.palprz.App;
import io.github.palprz.view.model.Translation;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

//TODO javadoc for class
public class TranslationController {

	@FXML
	private TableView<Translation> translationTable;
	
	@FXML
	private TableColumn<Translation, String> searchedWordColumn;

	@FXML
	private TableColumn<Translation, String> foundWordColumn;
	
	private App app;
	
	@FXML
	private void initialize() {
		searchedWordColumn.setCellValueFactory( cellData -> cellData.getValue().getSearchedWord() );
		foundWordColumn.setCellValueFactory( cellData -> cellData.getValue().getFoundWord() );
	}
	
	public void setApp( App app ) {
		this.app = app;
		translationTable.setItems( app.getTranslations() );
	}
}
