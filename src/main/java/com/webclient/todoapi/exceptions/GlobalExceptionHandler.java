package com.webclient.todoapi.exceptions;

import com.webclient.todoapi.models.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ApiResponse handleTodoException (TodoException ex){

        ApiResponse response = new ApiResponse();
        response.setCode("500");
        response.setTitle("ERROR");
        response.setMessage(ex.getMessage());
        response.setData(null);

        return response;
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse handleGeneralException(Exception ex) {

        ApiResponse response = new ApiResponse();
        response.setCode("500");
        response.setTitle("INTERNAL SERVER ERROR");
        response.setMessage("Something went wrong");
        response.setData(null);

        return response;
    }

}
