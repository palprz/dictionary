package io.github.palprz;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	private static final String CSS_URL = "/io/github/palprz/resource/application.css";

	public static void main( String[] args ) {
		launch( args );
	}

	@Override
	public void start( Stage primaryStage ) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation( App.class.getResource( "App.fxml" ) );
		BorderPane root = ( BorderPane ) loader.load();
		Scene scene = new Scene( root );
		scene.getStylesheets().add( getClass().getResource( CSS_URL ).toExternalForm() );
		primaryStage.setScene( scene );
		primaryStage.show();
	}

}
