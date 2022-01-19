package com.example.diploma.service;

import com.example.diploma.entity.Roles;
import com.example.diploma.entity.User;
import com.example.diploma.repas.IRolesRep;
import com.example.diploma.repas.IUserRep;
import com.example.diploma.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.regex.Pattern;

@Service
public class AuthServiceIml implements IAuthService {
    @Autowired
    IUserRep userRep;
    @Autowired
    IRolesRep roleRep;
    @Autowired
    PasswordEncoder passwordEncoder;

    private final String REGEX_PASSWORD = "\\S{6,}";
    @Override
    public boolean isValidUserPassword(String password) {
        Pattern pattern = Pattern.compile( REGEX_PASSWORD);
        return pattern.matcher(password).matches();
    }

    @Override
    public boolean isValidUserLogin(String login) {
        if (userRep.existsByLogin(login)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidUserEmail(String email) {
        if (userRep.existsByEmail(email))
            return false;
        return true;
    }

    @Override
    public void registerUser(User user){
        User userNew = new User(user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getCityName(),
                user.getCountryName(),
                user.getPhone(),
                user.getEmail(),
                user.getLogin(),
                passwordEncoder.encode(user.getPassword()));

        String rolesRequest = "";
        Roles role;
        if (rolesRequest == null || rolesRequest.isEmpty()) {
            role = roleRep.findByRoleName("ROLE_USER").orElseThrow(() -> new RuntimeException("Роли ROLE_USER не существует"));
        } else {
            switch (rolesRequest) {
                case "ROLE_ADMIN":
                    role = roleRep
                            .findByRoleName("ROLE_ADMIN")
                            .orElseThrow(() -> new RuntimeException("Роли ROLE_ADMIN не существует"));
                    break;
                default:
                    role = roleRep
                            .findByRoleName("ROLE_USER")
                            .orElseThrow(() -> new RuntimeException("Роли ROLE_USER не существует"));
            }
        }
        userNew.setRole(role);
        userRep.save(userNew);
    }
}
