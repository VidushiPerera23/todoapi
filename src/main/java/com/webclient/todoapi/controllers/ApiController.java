package com.webclient.todoapi.controllers;

import com.webclient.todoapi.models.Todo;
import com.webclient.todoapi.models.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class ApiController {

    @GetMapping
    public Object getAllTodos(){

        String url = "https://jsonplaceholder.typicode.com/todos";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class); //Send a GET request to this URL and give me the response as a Java object

    }

//    @GetMapping("/{userId}")
//    public Object getTodoByUser(@PathVariable int userId) {
//
//        String url = "https://jsonplaceholder.typicode.com/todos?userId=" + userId;
//
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(url, Object.class);
//    }
//
//    @GetMapping("/mapped")
//    public ApiResponse getMappedData(){
//
//        String url = "https://jsonplaceholder.typicode.com/todos";
//
//        RestTemplate restTemplate = new RestTemplate();
//        Todo[] todos =  restTemplate.getForObject(url, Todo[].class);
//
//        List<UserDetails> userDetailsList = Arrays.stream(todos)
//                .map(todo -> {
//                    UserDetails user = new UserDetails();
//                    user.setUser_id(todo.getUserId());
//                    user.setDescription(todo.getTitle());
//                    user.setStatus(todo.isCompleted()?"ACTIVE":"INACTIVE");
//                    return user;
//                })
//                .toList();
//
//        TodoData todoData = new TodoData();
//        todoData.setUser_details(userDetailsList);
//
//        ApiResponse response = new ApiResponse();
//        response.setCode("0000");
//        response.setTitle("SUCCESS");
//        response.setMessage("");
//        response.setData(todoData);
//
//        System.out.println("TITLE:"+ response.getTitle());
//        System.out.println("MESSAGE:"+ response.getMessage());
//
//        return response;

    @GetMapping("/{userId}")
    public ApiResponse getTodoByUser(@PathVariable int userId){

        String url = "https://jsonplaceholder.typicode.com/todos?userId="+userId;

        RestTemplate restTemplate =new RestTemplate();
        Todo[] todos = restTemplate.getForObject(url, Todo[].class);

        List<UserDetails>userDetailsList = mapTodosToUserDetails(todos);

        return buildResponse(userDetailsList);
    }

    @GetMapping("/mapped")
    public ApiResponse getMappedData() {

        String url = "https://jsonplaceholder.typicode.com/todos";

        RestTemplate restTemplate = new RestTemplate();
        Todo[] todos = restTemplate.getForObject(url, Todo[].class);

        List<UserDetails> userDetailsList = mapTodosToUserDetails(todos);

        return buildResponse(userDetailsList);
    }

    private List<UserDetails> mapTodosToUserDetails(Todo[] todos) {

        return Arrays.stream(todos)
                .map(todo -> {
                    UserDetails user = new UserDetails();
                    user.setUser_id(todo.getUserId());
                    user.setDescription(todo.getTitle());
                    user.setStatus(todo.isCompleted() ? "ACTIVE" : "INACTIVE");
                    return user;
                })
                .toList();
    }

    private ApiResponse buildResponse(List<UserDetails> userDetailsList) {

        TodoData todoData = new TodoData();
        todoData.setUser_details(userDetailsList);

        ApiResponse response = new ApiResponse();
        response.setCode("0000");
        response.setTitle("SUCCESS");
        response.setMessage("");
        response.setData(todoData);

        return response;
    }

}

