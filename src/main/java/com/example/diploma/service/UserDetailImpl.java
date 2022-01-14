package com.example.diploma.service;

import com.example.diploma.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailImpl implements UserDetails {

    private Long id;
    private String login;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String cityName;
    private String countryName;
    private String email;
    private String phone;
    private boolean active;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailImpl(Long id, String login, String password, String firstName, String lastName, String middleName, String cityName, String countryName, String email, String phone, boolean active,  Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.cityName = cityName;
        this.countryName = countryName;
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.authorities = authorities;
    }

    public static UserDetailImpl build(User user){
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println(user.getRole().getRoleName());
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------");
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return new UserDetailImpl(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getCityName(),
                user.getCountryName(),
                user.getEmail(),
                user.getPhone(),
                user.isActive(),
                authorityList
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
