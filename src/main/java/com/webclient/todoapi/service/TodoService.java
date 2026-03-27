package com.webclient.todoapi.service;

import com.webclient.todoapi.models.Todo;
import com.webclient.todoapi.models.TodoData;
import com.webclient.todoapi.models.UserDetails;
import com.webclient.todoapi.models.request.TodoRequest;
import com.webclient.todoapi.models.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private WebClient webClient;

    public ApiResponse getTodos(TodoRequest request){

        String url;

        if (request.getUserId() ==null){
            url = "https://jsonplaceholder.typicode.com/todos";
        } else {
            url = "https://jsonplaceholder.typicode.com/todos?userId=" + request.getUserId();
        }

        Todo[] todos =  webClient.get().uri(url).retrieve().bodyToMono(Todo[].class).block();

        List<UserDetails> userDetailsList = mapTodosToUserDetails(todos);

        return buildResponse(userDetailsList);
    }

    private List<UserDetails> mapTodosToUserDetails(Todo[] todos) {

        List<UserDetails> userList =new ArrayList<>();

        for (Todo todo : todos){
            UserDetails user = new UserDetails();
            user.setUser_id(todo.getUserId());
            user.setDescription(todo.getTitle());
            user.setStatus(todo.isCompleted() ? "ACTIVE" : "INACTIVE");
            userList.add(user);
        }

        return userList;
    }

    private ApiResponse buildResponse(List<UserDetails> userDetailsList) {

        TodoData todoData = new TodoData();
        todoData.setUserDetails(userDetailsList);

        ApiResponse response = new ApiResponse();
        response.setCode("0000");
        response.setTitle("SUCCESS");
        response.setMessage("");
        response.setData(todoData);

        return response;
    }
}
