package com.webclient.todoapi.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Data
public class TodoData {
   @JsonProperty("user_details") //when converting to JSON use the name user_details
   private List<UserDetails>user_details;
}
