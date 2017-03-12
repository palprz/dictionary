package io.github.palprz.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class WordMap {

	@Id
	private ObjectId id = new ObjectId();

	@Reference
	private Word searchWord;

	@Reference
	private Word translation;

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
