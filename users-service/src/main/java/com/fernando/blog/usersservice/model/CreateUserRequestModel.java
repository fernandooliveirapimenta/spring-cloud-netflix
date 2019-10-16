package com.fernando.blog.usersservice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

    @NotNull(message = "first name cannot be null")
    @Size(min = 2, message = "First name must not be less than two")
    private String firstName;
    @NotNull(message = "last name cannot be null")
    private String lastName;
    @NotNull(message = "password cannot be null")
    private String passwordName;
    @NotNull(message = "email cannot be null")
    @Email
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswordName() {
        return passwordName;
    }

    public void setPasswordName(String passwordName) {
        this.passwordName = passwordName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
