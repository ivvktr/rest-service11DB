package com.example.restservice11db;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class User {
    private String login;
    private String password;
    private String email;
    private String date;
    public User() {}

    public String getLogin() {
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
