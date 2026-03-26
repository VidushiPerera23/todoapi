package com.webclient.todoapi.models;

import lombok.Data;

@Data
public class Todo {

    private int userId;
    private String title;
    private boolean completed;

}
