package com.fernando.blog.usersservice.dto;


import com.fernando.blog.usersservice.model.AlbumResponseModel;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String passwordName;
    private String email;
    private String userId;
    private String encryptedPassword;
    private List<AlbumResponseModel> albums;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public List<AlbumResponseModel> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumResponseModel> albums) {
        this.albums = albums;
    }
}
