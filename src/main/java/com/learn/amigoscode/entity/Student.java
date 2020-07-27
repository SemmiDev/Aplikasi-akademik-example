package com.learn.amigoscode.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
@Entity
public class Student {

    @Id
    @Size(min = 5, max = 5, message = "min dan max panjang nisn adalah 5 karakter")
    private String nisn;

    @NotNull
    @NotEmpty
    @Size(min = 2 ,max = 25, message = "max panjang nama adalah 25 karakter")
    private String name;

    @NotEmpty
    @Size(min = 3, max = 20, message = "min = 3 dan max = 20 karakter")
    private String major;

    @NotNull
    @Min(value = 1, message = "minimal kelas 1")
    @Max(value = 3, message = "maksimal kelas 3")
    private Integer kelas;
}