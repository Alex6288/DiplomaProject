package com.example.diploma.entity;


import org.springframework.stereotype.Component;

@Component
public class ThymeMath {
    public String ceil(double value){
        return String.format("%.2f",value);
    }
}
