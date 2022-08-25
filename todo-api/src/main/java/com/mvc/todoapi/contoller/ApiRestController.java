package com.mvc.todoapi.contoller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.todoapi.domain.Todo;
import com.mvc.todoapi.service.TodoService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ApiRestController {

    private final  TodoService todoService;

    
    @GetMapping("todos/{id}")
    public Todo getTodo(@PathVariable String todoId) throws Exception {
        return todoService.getTodo(todoId);
    }
    
    @PutMapping("todos/{id}")
    public Todo updateTodo(@PathVariable String todoId, @ModelAttribute("todo") Todo todo, @RequestParam String apikey) throws Exception {
        return todoService.updateTodo(todoId, todo, apikey);
    }
    
    @DeleteMapping("todos/{id}")
    public String deleteTodo(@PathVariable String todoId, @RequestParam String apikey) throws Exception {
        todoService.deleteTodo(todoId, apikey);
        return "Delete complete";
    }
    
    @PostMapping("todos")
    public Todo createTodo(@ModelAttribute("todo") Todo todo, @RequestParam String apikey) throws Exception {
       
        return  todoService.createTodo(todo.getName(), apikey);
    }
    
    @GetMapping("todos")
    public List<Todo> listTodos(@PathVariable String todoId) throws Exception {
    	int limit =0;
    	String skip = "";
        return todoService.getListTodo(limit, skip);
    }
    
    @GetMapping("temp")
    public String temp() {
        return "FINAL TEST";
    }


}