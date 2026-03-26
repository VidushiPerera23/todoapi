package com.webclient.todoapi.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoData {
   @JsonProperty("user_details") //when converting to JSON use the name user_details
   private List<UserDetails> userDetails;
}
