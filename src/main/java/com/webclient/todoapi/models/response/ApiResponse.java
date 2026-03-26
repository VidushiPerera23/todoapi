package com.webclient.todoapi.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.webclient.todoapi.models.TodoData;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS) //Include ALL fields in the JSON, even if they are empty or null
@JsonPropertyOrder({ "code", "title", "message", "data" })
public class ApiResponse {

    private String code;
    private String title;
    private String message;
    private TodoData data;

}
