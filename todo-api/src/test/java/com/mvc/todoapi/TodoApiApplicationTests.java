package com.mvc.todoapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.todoapi.domain.Todo;
import com.mvc.todoapi.service.TodoService;

@SpringBootTest
class TodoApiApplicationTests {
	
	private TodoService todoService;
	
	@Autowired
	public TodoApiApplicationTests(TodoService todoService) {
		this.todoService=todoService;		
	}
	
//	@Test
	void testGetTodo() throws Exception{
		Todo todo = todoService.getTodo("1");
		
		System.out.println(todo);
		assertEquals(1, todo.getId());
		assertEquals("get food", todo.getName());
	}
	
//	@Test
	void testUpdateTodo() throws Exception{
		Todo todo = new Todo();
		todo.setName("my todo's new name");
		todo.setCompleted(false);
		todo= todoService.updateTodo("1" , todo, "123");
		
		System.out.println(todo);
		assertEquals(9000, todo.getId());
		assertEquals("It's Over 9000!!!", todo.getName());
	}
	
	@Test
	void testDeleteTodo() throws Exception{
		
		assertEquals(204, todoService.deleteTodo("1" ,  "123"));
	}
	
//	@Test
	void testCreateTodo() throws Exception{
		Todo todo = todoService.createTodo("my todo's name" ,  "123");
		assertEquals(9000, todo.getId());
	}
	
//	@Test
	void testGetListTodo() throws Exception{
		 List<Todo>  todo = todoService.getListTodo(1 ,  "123");
		
		assertEquals(1,  todo.get(0).getId());
	}

}
