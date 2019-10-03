package br.com.fernando.blog.ws;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class UserDetailsRequest {

    @NotNull(message = "firstName nao pode sser null")
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String userId;

    @NotNull
    @Size(min = 8, max = 16)
    private String password;

}
