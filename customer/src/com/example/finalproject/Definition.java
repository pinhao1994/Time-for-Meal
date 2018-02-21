package com.example.finalproject;


/**
 * Defines definition domain objects.
 */
public class Definition {
	private String title;
	private String description;

	public Definition() {
		super();
	}

	public Definition(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
