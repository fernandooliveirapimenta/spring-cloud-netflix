package br.com.fernando.blog.ws;

import lombok.*;

@Data
@Builder(toBuilder = true)
@ToString
public class UserRest {

    @With
    private String firstName;
    private String lastName;
    private String email;
    private String userId;

}
