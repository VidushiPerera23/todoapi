package com.webclient.todoapi.controllers;

import com.webclient.todoapi.models.request.TodoRequest;
import com.webclient.todoapi.models.response.ApiResponse;
import com.webclient.todoapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class ApiController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ApiResponse getTodos(@RequestBody TodoRequest request){
        return todoService.getTodos(request);
    }

}

