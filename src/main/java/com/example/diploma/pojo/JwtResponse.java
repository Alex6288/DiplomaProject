package com.example.diploma.pojo;

import com.example.diploma.entity.Roles;

import java.util.List;
import java.util.Set;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long userId;
    private String login;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Long userId, String login, String email, List<String> roles) {
        this.token = token;
        this.userId = userId;
        this.login = login;
        this.email = email;
        this.roles = roles;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
