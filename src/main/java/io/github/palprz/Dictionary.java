package io.github.palprz;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Launch class to start 'Dictionary' application.
 * @author Przemyslaw Paluch
 */
public class Dictionary extends Application {

	private static final String CSS_URL = "/io/github/palprz/resource/dictionary.css";
	private static final String MAIN_FXML_NAME = "Dictionary.fxml";

	/**
	 * Main method.
	 * @param args Provided arguments.
	 */
	public static void main( String[] args ) {
		launch( args );
	}

	@Override
	public void start( Stage primaryStage ) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation( Dictionary.class.getResource( MAIN_FXML_NAME ) );
		AnchorPane root = ( AnchorPane ) loader.load();
		Scene scene = new Scene( root );
		scene.getStylesheets().add( getClass().getResource( CSS_URL ).toExternalForm() );
		primaryStage.setScene( scene );
		primaryStage.show();
	}
}
