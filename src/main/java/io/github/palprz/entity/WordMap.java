package io.github.palprz.entity;

//TODO javadoc for class
public class WordMap {

	private Integer id;
	private String searchWord;
	private String translation;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord( String searchWord ) {
		this.searchWord = searchWord;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation( String translation ) {
		this.translation = translation;
	}
}
