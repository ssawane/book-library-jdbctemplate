package com.mara.library.models;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Format: Name Lastname Surname")
    private String name;
    @Min(value = 1900, message = "Insert correct birth date")
    @Max(value = 2023, message = "Insert correct birth date")
    private int dateOfBirth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
