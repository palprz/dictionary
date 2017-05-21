## Dictionary [February - May 2017]

![dictionary][dictionary]

Desktop application for read, write, edit and remove translations.

This application isn't a big one and the main goal isn't containing unique/revealing solutions. The goals for this project are:
1. Gather new skills with using new technologies:
	- JavaFX (create modern view instead of ugly solution which I could get with using Swing)
	- NoSQL database (MongoDB) for Java
  
2. Get bigger experience with using GitHub/tools dedicated for it:
	- Codacy to monitoring code quality (alternative for SonarQube)
	- creating isssues on GitHub (alternative for creating issues with using JIRA)
  
3. Working with typical company workflow:
	- using feature branches to separate specific code and to be easier reference them
	- creating issues and reference pull requests to them
	- report bugs with using issues GitHub feature
	- monitor progress of my work in different ways

## Codacy (quality code tool):
Codacy contains 17 issues connected with not used methods, but JavaFX use them in FXML files. I was talking with Codacy support and they know that issue.
https://www.codacy.com/app/palprz/dictionary/dashboard

## Technologies and tools:
- Java 7
- Eclipse
- Maven
- JUnit 
- MongoDB
- JavaFX (with plugin e(fx)clipse and Scene Builder 8.0)
- Codacy
- mLab

## Features

##### Translations
**Description:** User can add, edit, delete and display translations.
![translation_window][translation_window]

##### Languages
**Description:** User can add, edit, remove and display languages. Each translation has got map between languages (i.e. eng-pl)
![language_window][language_window]

##### Search translation by word and language
**Description:** User can find translation for specific search word and language. User can find more than 1 translation.
![found_translation][found_translation]

##### Swap search word with selected translation
**Description:** User can swap translation (word and language) with current search word (word and language).
![swap][swap]

##### Button with reset database
**Description:** User can remove all translations and languages from database from top bar.
![reset_database][reset_database]

##### Action from context menu
**Description:** User can add, edit and remove translation from Dictionary (main) window by clicking RMB on table with translations and choose the action. 
If action will be add, application will take current search word and language connected with it, open Translation Window and populate these 2 fields with values.
If action will be edit, application will take current serch word with related language and selected item in table, open Translation Window and populate these 4 fields with values.
![context_menu_edit][context_menu_edit]
![context_menu_add][context_menu_add]

##### Validation
**Dictionary:** All actions are connected with validators. If the validation will fail, user will see message about it at the bottom of the window.
![validation][validation]


[dictionary]: https://github.com/palprz/dictionary/blob/master/github-screenshots/dictionary.png
[translation_window]: https://github.com/palprz/dictionary/blob/master/github-screenshots/translation_window.png
[language_window]: https://github.com/palprz/dictionary/blob/master/github-screenshots/language_window.png
[found_translation]: https://github.com/palprz/dictionary/blob/master/github-screenshots/found_translation.png
[swap]: https://github.com/palprz/dictionary/blob/master/github-screenshots/swap.png
[reset_database]: https://github.com/palprz/dictionary/blob/master/github-screenshots/reset_database.png
[context_menu_edit]: https://github.com/palprz/dictionary/blob/master/github-screenshots/context_menu_edit.png
[context_menu_add]: https://github.com/palprz/dictionary/blob/master/github-screenshots/context_menu_add.png
[validation]: https://github.com/palprz/dictionary/blob/master/github-screenshots/validation.png