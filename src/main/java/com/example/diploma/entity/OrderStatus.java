package com.example.diploma.entity;

public enum OrderStatus {
    OPEN("Создан"), SEND("Отправлен"), RECEIVED("Доставлен"), REJECTED("Отменен");
    private String statusName;

    OrderStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
