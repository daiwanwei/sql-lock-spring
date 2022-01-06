package com.example.sqllockspring.dto;

public class UpdatePetDto {
    private String name;

    public UpdatePetDto(String name) {
        this.name = name;
    }

    public UpdatePetDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
