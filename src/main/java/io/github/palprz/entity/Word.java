package io.github.palprz.entity;

public class Word {

	private Integer id;
	private String name;

	public Word() {
	}
	
	public Word( final String nameVal ) {
		this.name = nameVal;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}
}
