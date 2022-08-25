package com.mvc.todoapi.domain;


import lombok.Data;

@Data
public class Todo {

	private int id;
	private String name;
	private boolean completed;
	private String completed_at;
	private String created_at;
	private String updated_at;
	private User user;
	
	public Todo(int id, String name, boolean completed, String completed_at, String created_at, String updated_at, User user) {
		this.id = id;
		this.name = name;
		this.completed = completed;
		this.completed_at = completed_at;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.user = user;
	}

	public Todo() {
	}

	

}