package com.todo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todolist")
public final class Todo {

	@Id
	private String id;

	private String description;
	@Indexed(unique = true)
	private String title;

	public Todo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void update(String title, String description) {

		this.title = title;
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Todo[id=%s, description=%s, title=%s]", this.id, this.description, this.title);
	}

}
