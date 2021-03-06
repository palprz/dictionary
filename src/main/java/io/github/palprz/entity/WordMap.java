package io.github.palprz.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity( "wordMap" )
public class WordMap {

	@Id
	private ObjectId id = new ObjectId();

	@Reference( "searchWord" )
	private Word searchWord;

	@Reference( "translation" )
	private Word translation;

	public WordMap() {
		//empty constructor
	}

	public WordMap( final Word searchWordVal, final Word translationVal ) {
		searchWord = searchWordVal;
		translation = translationVal;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId( final ObjectId id ) {
		this.id = id;
	}

	public Word getSearchWord() {
		return searchWord;
	}

	public void setSearchWord( final Word searchWord ) {
		this.searchWord = searchWord;
	}

	public Word getTranslation() {
		return translation;
	}

	public void setTranslation( final Word translation ) {
		this.translation = translation;
	}
}
