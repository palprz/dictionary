package io.github.palprz.view;

import java.io.IOException;

import io.github.palprz.Dictionary;
import io.github.palprz.entity.enums.Action;
import io.github.palprz.resource.Constant;
import io.github.palprz.view.dictionary.DictionaryWindowController;
import io.github.palprz.view.translation.TranslationWindowController;
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
		final FXMLLoader loader = new FXMLLoader( Dictionary.class.getResource( Constant.DICTIONARY_FXML_URL ) );
		final AnchorPane root = ( AnchorPane ) loader.load();
		final Scene scene = new Scene( root );
		scene.getStylesheets().add( getClass().getResource( Constant.CSS_URL ).toExternalForm() );
		primaryStage.setScene( scene );
		primaryStage.setTitle( Constant.DICTIONARY_WINDOW_TITLE );
		primaryStage.show();
	}

	/**
	 * Create the stage for Translation window.
	 *
	 * @param dictionaryCtrl The controller from Dictionary window
	 * @param action The action to do after create Translation window
	 * @throws IOException throws from loading AnchorPane from FXMLLoader.
	 */
	public void createTranslation( final DictionaryWindowController dictionaryCtrl, final Action action )
	throws IOException {
		final FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( Constant.TRANSLATION_FXML_URL ) );
		final Parent root1 = ( Parent ) fxmlLoader.load();
		final Scene scene = new Scene( root1 );
		scene.getStylesheets().add( getClass().getResource( Constant.CSS_URL ).toExternalForm() );
		final Stage stage = new Stage();
		stage.setTitle( Constant.TRANSLATION_WINDOW_TITLE );
		stage.setScene( scene );
		stage.show();

		final TranslationWindowController translationCtrl = fxmlLoader.<TranslationWindowController>getController();

		switch( action ) {
			case ADD:
				populateAddTranslationWindow( dictionaryCtrl, translationCtrl );
				break;
			case EDIT:
				populateEditTranslationWindow( dictionaryCtrl, translationCtrl );
				break;
			case REMOVE:
			case OPEN:
			default:
				break;
		}
	}

	/**
	 * Populate field in Translation window related with adding process.
	 *
	 * @param dictionaryCtrl The controller from Dictionary window
	 * @param translationCtrl The controller from Translation window
	 */
	private void populateAddTranslationWindow(
			final DictionaryWindowController dictionaryCtrl,
			final TranslationWindowController translationCtrl ) {
		translationCtrl.addSearchWordField.setText(
				dictionaryCtrl.searchWordField.getText() );

		translationCtrl.addSearchWordLangCombo.getSelectionModel().select(
				dictionaryCtrl.searchWordLangCombo.getSelectionModel().getSelectedItem() );
	}

	/**
	 * Populate field in Translation window related with editing process.
	 *
	 * @param dictionaryCtrl The controller from Dictionary window
	 * @param translationCtrl The controller from Translation window
	 */
	private void populateEditTranslationWindow(
			final DictionaryWindowController dictionaryCtrl,
			final TranslationWindowController translationCtrl ) {
		translationCtrl.editOldSearchWordField.setText(
				dictionaryCtrl.searchWordField.getText() );

		translationCtrl.editOldSearchWordLangCombo.getSelectionModel().select(
				dictionaryCtrl.searchWordLangCombo.getSelectionModel().getSelectedItem() );

		if ( dictionaryCtrl.translationTable.getSelectionModel().getSelectedItem() != null ) {
			translationCtrl.editOldTranslationField.setText(
					dictionaryCtrl.translationTable.getSelectionModel().getSelectedItem().getName().getValue() );

			translationCtrl.editOldTranslationLangCombo.getSelectionModel().select(
					dictionaryCtrl.translationTable.getSelectionModel().getSelectedItem().getLanguage() );
		}
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

	/**
	 * Create the stage for Info window.
	 * @throws IOException throws from loading AnchorPane from FXMLLoader.
	 */
	public void createInfo() throws IOException {
		final FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( Constant.INFO_FXML_URL ) );
		final Parent root1 = ( Parent ) fxmlLoader.load();
		final Scene scene = new Scene( root1 );
		scene.getStylesheets().add( getClass().getResource( Constant.CSS_URL ).toExternalForm() );
		final Stage stage = new Stage();
		stage.setTitle( Constant.INFO_WINDOW_TITLE );
		stage.setScene( scene );
		stage.show();
	}
}
