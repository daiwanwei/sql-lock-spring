package com.example.sqllockspring.dto;

public class AdoptDto {
    private String name;

    public AdoptDto(String name) {
        this.name = name;
    }

    public AdoptDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
