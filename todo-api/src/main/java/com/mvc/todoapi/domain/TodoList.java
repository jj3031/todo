package com.mvc.todoapi.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TodoList {
	
	private List<Todo> todoList = new ArrayList<Todo>();
	
}
