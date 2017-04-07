package io.github.palprz.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.utils.IndexType;

@Entity( "language" )
@Indexes(@Index(fields = @Field(value = "$**", type = IndexType.TEXT)))
public class Language {

	@Id
	private ObjectId id = new ObjectId();

	@Property( "name" )
	private String name;

	public Language() {
		//empty constructor
	}

	public Language( final String nameVal ) {
		name = nameVal;
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
}
