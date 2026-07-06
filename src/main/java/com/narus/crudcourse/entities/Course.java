package com.narus.crudcourse.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="courses")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Course(String name, Double price){
        this.name = name;
        this.price = price;
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;
    //default value true
    @Column(nullable = false)
    private boolean active = true;

}
