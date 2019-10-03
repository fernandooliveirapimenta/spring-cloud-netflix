package br.com.fernando.blog.ws;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class UpdateUserDetailsRequestModel {

    @NotNull(message = "firstName nao pode sser null")
    @With
    @NonNull
    private String firstName;

    @With
    @NonNull
    @NotNull
    private String lastName;

}
