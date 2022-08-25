package com.mvc.todoapi.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mvc.todoapi.domain.Todo;
import com.mvc.todoapi.domain.TodoList;

@Service
public class TodoService {
		
	public TodoService() {
		System.out.println(this.getClass());
	}
	
	public Todo getTodo(String todoId) throws Exception{
		
		String reqURL = "https://stoplight.io/mocks/dietfriends/todo-api/781080/todos/" + todoId;
		
		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setDoOutput(true);
		
		
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		String line = "";
		String result = "";
		
		while ((line = br.readLine()) != null) {
			result += line;
		}
		
		System.out.println("response body : " + result);
		
		
		Todo todo = new Gson().fromJson(result, Todo.class);
		        
		
        return todo;
	}
	
	
	public Todo updateTodo(String todoId, String apiKey) throws Exception{
		
		String reqURL = "https://stoplight.io/mocks/dietfriends/todo-api/781080/todos/" + todoId;
		
		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setDoOutput(true);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		StringBuilder sb = new StringBuilder();
		sb.append("apikey="+apiKey);
		bw.write(sb.toString());
		bw.flush();
		
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";
		String result = "";
		
		while ((line = br.readLine()) != null) {
			result += line;
		}
		System.out.println("response body : " + result);
		
		Todo todo = new Gson().fromJson(result, Todo.class);
        
        return todo;
	}
	
	
	
	public void deleteTodo(String todoId, String apiKey) throws Exception{
		
		String reqURL = "https://stoplight.io/mocks/dietfriends/todo-api/781080/todos/" + todoId;
		
		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("DELETE");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setDoOutput(true);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		StringBuilder sb = new StringBuilder();
		sb.append("apikey="+apiKey);
		sb.append("&todoId="+todoId);
		bw.write(sb.toString());
		bw.flush();
		
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);
	}
	
	public Todo createTodo(String todoName, String apiKey) throws Exception{
		
		String reqURL = "https://stoplight.io/mocks/dietfriends/todo-api/781080/todos";
		
		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setDoOutput(true);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		StringBuilder sb = new StringBuilder();
		sb.append("apikey="+apiKey);
		sb.append("&name="+todoName);
		sb.append("&completed=false");
		bw.write(sb.toString());
		bw.flush();
		
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";
		String result = "";
		
		while ((line = br.readLine()) != null) {
			result += line;
		}
		System.out.println("response body : " + result);
		
		Todo todo = new Gson().fromJson(result, Todo.class);
        
        return todo;
	}
	
	
	public List<Todo> getListTodo() throws Exception{
		
		String reqURL = "https://stoplight.io/mocks/dietfriends/todo-api/781080/todos";
		
		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setDoOutput(true);
		
		
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		String line = "";
		String result = "";
		
		while ((line = br.readLine()) != null) {
			result += line;
		}
		
		System.out.println("response body : " + result);
		

		TodoList todoList = new Gson().fromJson(result, TodoList.class);
		
		
        return todoList.getTodoList();
	}
	

	
}
