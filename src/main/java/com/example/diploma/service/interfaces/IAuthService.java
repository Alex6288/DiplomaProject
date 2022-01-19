package com.example.diploma.service.interfaces;

import com.example.diploma.entity.User;
import org.springframework.validation.BindingResult;

public interface IAuthService {
    void registerUser(User user);
    boolean isValidUserPassword(String password);
    boolean isValidUserLogin(String  login);
    boolean isValidUserEmail(String email);
}
