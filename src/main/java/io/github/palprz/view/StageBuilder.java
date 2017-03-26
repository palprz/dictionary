package io.github.palprz.view;

import java.io.IOException;

import io.github.palprz.Dictionary;
import io.github.palprz.resource.Constant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class for creating stages for different windows.
 */
public class StageBuilder {

	/**
	 * Create the stage for Dictionary window.
	 * @param primaryStage The primary stage getting during starting application.
	 * @throws IOException throws from loading AnchorPane from FXMLLoader.
	 */
	public void createDictionary( final Stage primaryStage ) throws IOException {
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation( Dictionary.class.getResource( Constant.DICTIONARY_FXML_URL ) );
		final AnchorPane root = ( AnchorPane ) loader.load();
		final Scene scene = new Scene( root );
		scene.getStylesheets().add( getClass().getResource( Constant.CSS_URL ).toExternalForm() );
		primaryStage.setScene( scene );
		primaryStage.setTitle( Constant.DICTIONARY_WINDOW_TITLE );
		primaryStage.show();
	}

	/**
	 * Create the stage for Translation window.
	 * @throws IOException throws from loading AnchorPane from FXMLLoader.
	 */
	public void createTranslation() throws IOException {
		final FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( Constant.TRANSLATION_FXML_URL ) );
		final Parent root1 = ( Parent ) fxmlLoader.load();
		final Scene scene = new Scene( root1 );
		scene.getStylesheets().add( getClass().getResource( Constant.CSS_URL ).toExternalForm() );
		final Stage stage = new Stage();
		stage.setTitle( Constant.TRANSLATION_WINDOW_TITLE );
		stage.setScene( scene );
		stage.show();
	}

	/**
	 * Create the stage for Language window.
	 * @throws IOException throws from loading AnchorPane from FXMLLoader.
	 */
	public void createLanguage() throws IOException {
		final FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( Constant.LANGUAGE_FXML_URL ) );
		final Parent root1 = ( Parent ) fxmlLoader.load();
		final Scene scene = new Scene( root1 );
		scene.getStylesheets().add( getClass().getResource( Constant.CSS_URL ).toExternalForm() );
		final Stage stage = new Stage();
		stage.setTitle( Constant.LANGUAGE_WINDOW_TITLE );
		stage.setScene( scene );
		stage.show();
	}
}
