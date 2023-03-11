package com.mara.library.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty
    @Size(min = 2, max = 50, message = "Book title size should be between 2 and 50 characters")
    private String title;
    @NotEmpty
    @Size(min = 2, max = 50, message = "Author name size should be between 2 and 50 characters")
    private String author;
    @Min(value = 0, message = "Insert correct value of year")
    @Max(value = 2023, message = "Insert correct value of year")
    private int year;
    private int person_id;

    public boolean isRented() {
        return person_id != 0;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRenter() {
        return person_id;
    }

    public void setRenter(int person_id) {
        this.person_id = person_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
