package com.example.sqllockspring.dto;

public class UpdateUserDto {
    private String userName;

    public UpdateUserDto(String userName) {
        this.userName = userName;
    }

    public UpdateUserDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
