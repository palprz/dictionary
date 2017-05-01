package io.github.palprz.resource;


public class Constant {

	/**
	 * URLs.
	 */
	public static final String CSS_URL = "/io/github/palprz/resource/dictionary.css";
	public static final String DICTIONARY_FXML_URL = "/io/github/palprz/view/dictionary/Dictionary.fxml";
	public static final String TRANSLATION_FXML_URL = "/io/github/palprz/view/translation/Translation.fxml";
	public static final String LANGUAGE_FXML_URL = "/io/github/palprz/view/language/Language.fxml";
	public static final String INFO_FXML_URL = "/io/github/palprz/view/info/Info.fxml";

	/**
	 * Titles, labels etc.
	 */
	public static final String DICTIONARY_WINDOW_TITLE = "Dictionary window";
	public static final String TRANSLATION_WINDOW_TITLE = "Translation window";
	public static final String LANGUAGE_WINDOW_TITLE = "Language window";
	public static final String INFO_WINDOW_TITLE = "Info window";

	/**
	 * Messages to display in status bar.
	 */
	// Dictionary window
	public static final String DICTIONARY_RESET_DB_SUCCESS_MSG = "Complete reseting database";
	public static final String DICTIONARY_AGREE_RESET_DB_MSG = "You need to click agree checbox to be able to reset database";
	public static final String DICTIONARY_TRANSLATE_SUCCESS_MSG = "Found translations for'%s'";
	public static final String DICTIONARY_NO_WORD_TRANSLATE_MSG = "Search word '%s' for define language doesn't exist in the database";

	// Language window
	public static final String LANGUAGE_ADD_SUCCESS_MSG = "Language '%s' added";
	public static final String LANGUAGE_EDIT_SUCCESS_MSG = "Language '%s' edited by '%s'";
	public static final String LANGUAGE_REMOVE_SUCCESS_MSG = "Language '%s' removed";
	public static final String LANGUAGE_CONNECTED_LANGUAGE_MSG = "This language is connected with %s word(s) and can't be remove. Please remove connected words!";
	public static final String LANGUAGE_ADD_EMPTY_LANGUAGE_WARNING_MSG = "WARNING: Language name is empty - cannot be add";
	public static final String LANGUAGE_ADD_LANGUAGE_EXISTS_WARNING_MSG = "WARNING: Language '%s' already exists in the database";
	public static final String LANGUAGE_EDIT_EMPTY_LANGUAGE_WARNING_MSG = "WARNING: Language name is empty - cannot be edit";


	// Translation window
	public static final String TRANSLATION_ADD_SUCCESS_MSG = "Translation '%s' added";
	public static final String TRANSLATION_EDIT_SUCCESS_MSG = "Translation '%s' edited by '%s'";
	public static final String TRANSLATION_REMOVE_SUCCESS_MSG = "Translation '%s' removed";
}
