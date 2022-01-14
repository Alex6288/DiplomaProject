package com.example.diploma.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "login"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "В имени должно быть от 2 до 30 симолов")
    private String firstName;
    private String middleName;
    @NotEmpty(message = "Поле Фамилия не должно быть пустым")
    @Size(min = 2, max = 30, message = "В фамилии должно быть от 2 до 30 симолов")
    private String lastName;
    @NotEmpty(message = "Поле Фамилия не должно быть пустым")
    @Size(min = 2, max = 30, message = "В названии города должно быть от 2 до 30 симолов")
    private String cityName;
    @NotEmpty(message = "Поле Фамилия не должно быть пустым")
    @Size(min = 2, max = 30, message = "В названии страны должно быть от 2 до 30 симолов")
    private String countryName;
    @Pattern(regexp = "\\d{11}", message = "Введите телефон в формате 78889998877")
    private String phone;
    private String email;
    @NotEmpty(message = "Поле Фамилия не должно быть пустым")
    @Size(min = 3, max = 30, message = "В логине должно быть от 3 до 30 симолов")
    private String login;
    @NotEmpty(message = "Поле Фамилия не должно быть пустым")
    private String password;
    private boolean active;

    @ManyToOne
    private Roles role;

    public User(String firstName, String middleName, String lastName, String cityName, String countryName, String phone, String email, String login, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.cityName = cityName;
        this.countryName = countryName;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.active = true;
    }

    public User() {
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void changeActive(){
        this.active = !active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
