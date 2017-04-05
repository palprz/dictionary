package io.github.palprz.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexType;

@Entity( "word" )
@Indexes(@Index(fields = @Field(value = "$**", type = IndexType.TEXT)))
public class Word {

	@Id
	private ObjectId id = new ObjectId();

	@Property( "name" )
	private String name;

	@Reference( "lang" )
	private Language language;

	public Word() {
	}

	public Word( final String nameVal, final Language langVal ) {
		name = nameVal;
		language = langVal;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId( final ObjectId id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( final String name ) {
		this.name = name;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage( final Language language ) {
		this.language = language;
	}
}
