package io.github.palprz;

import java.io.IOException;

import io.github.palprz.view.TranslationController;
import io.github.palprz.view.model.Translation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {

	private static final String CSS_URL = "/io/github/palprz/resource/application.css";
	private static final String MAIN_FXML_NAME = "App.fxml";

	private static ObservableList< Translation > translations = FXCollections.observableArrayList();

	public static void main( String[] args ) {
		translations.add( new Translation( "hello", "czesc" ) );
		translations.add( new Translation( "good morning", "dzien dobry" ) );

		launch( args );
	}

	@Override
	public void start( Stage primaryStage ) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation( App.class.getResource( MAIN_FXML_NAME ) );
		AnchorPane root = ( AnchorPane ) loader.load();
		Scene scene = new Scene( root );
		scene.getStylesheets().add( getClass().getResource( CSS_URL ).toExternalForm() );
		primaryStage.setScene( scene );
		primaryStage.show();

		TranslationController ctrl = loader.getController();
		ctrl.setApp( this );
	}

	public ObservableList< Translation > getTranslations() {
		return translations;
	}

}
