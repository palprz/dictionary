package io.github.palprz;

import java.io.IOException;

import io.github.palprz.db.Database;
import io.github.palprz.view.StageBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launch class to start 'Dictionary' application.
 * @author Przemyslaw Paluch
 */
public class Dictionary extends Application {

	private static final StageBuilder STAGE_BUILDER = new StageBuilder();

	/**
	 * Main method.
	 * @param args Provided arguments.
	 */
	public static void main( final String[] args ) {
		Database.init();
		launch( args );
	}

	@Override
	public void start( final Stage primaryStage ) throws IOException {
		STAGE_BUILDER.createDictionary( primaryStage );
	}
}
