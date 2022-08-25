package com.mvc.todoapi.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mvc.todoapi.domain.Todo;

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
	
	
	public Todo updateTodo(String todoId, Todo todo, String apiKey) throws Exception{
		
		String reqURL = "https://stoplight.io/mocks/dietfriends/todo-api/781080/todos/" + todoId;


		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("apikey", "123");
		conn.setDoOutput(true);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		
		JsonObject param = new JsonObject();
        param.addProperty("name", todo.getName());
        param.addProperty("completed", todo.isCompleted());
        bw.write( param.toString());
		bw.flush();
		
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		String line = "";
		String result = "";
		
		while ((line = br.readLine()) != null) {
			result += line;
		}
		System.out.println("response body : " + result);
		
		todo = new Gson().fromJson(result, Todo.class);
        
        return todo;
	}
	
	
	
	public int deleteTodo(String todoId, String apiKey) throws Exception{
		
		String reqURL = "https://stoplight.io/mocks/dietfriends/todo-api/781080/todos/"+todoId;
		
		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("DELETE");
//		conn.setRequestProperty("Content-type", "application/json");
//		conn.setRequestProperty("apikey", "123");
//		conn.setDoOutput(true);
		
		
		System.out.println(conn.getResponseMessage());
		
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);
		
		return responseCode;
	}
	
	
	public Todo createTodo(String todoName, String apiKey) throws Exception{
		
		String reqURL = "https://stoplight.io/mocks/dietfriends/todo-api/781080/todos";
		
		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("apikey", "123");
		conn.setDoOutput(true);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		
		JsonObject param = new JsonObject();
        param.addProperty("name", todoName);
        param.addProperty("completed", false);
        bw.write( param.toString());
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
	
	
	public List<Todo> getListTodo(int limit , String skip) throws Exception{
		
		String reqURL = "https://stoplight.io/mocks/dietfriends/todo-api/781080/todos?limit="+limit+"&skip="+skip;
		
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
			
		
		Type listType = new TypeToken<ArrayList<Todo>>(){}.getType();
		ArrayList<Todo> todoList = new Gson().fromJson(result, listType); 
		System.out.println(todoList);
		
        return todoList;
	}
	

	
}
