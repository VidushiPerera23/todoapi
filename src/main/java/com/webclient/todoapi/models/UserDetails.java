package com.webclient.todoapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "user_id", "description", "status" })
public class UserDetails {
    @JsonProperty("user_id")
    private int user_id;
    private String description;
    private String status;
}
