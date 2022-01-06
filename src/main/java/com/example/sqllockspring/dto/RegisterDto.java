package com.example.sqllockspring.dto;

public class RegisterDto {
    private String userName;

    public RegisterDto(String userName) {
        this.userName = userName;
    }

    public RegisterDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
