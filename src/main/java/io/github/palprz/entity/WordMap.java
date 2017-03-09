package io.github.palprz.entity;

public class WordMap {

	private Integer id;
	private Word searchWord;
	private Word translation;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Word getSearchWord() {
		return searchWord;
	}

	public void setSearchWord( Word searchWord ) {
		this.searchWord = searchWord;
	}

	public Word getTranslation() {
		return translation;
	}

	public void setTranslation( Word translation ) {
		this.translation = translation;
	}
}
