package com.narus.crudcourse.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CourseRequest {

     private  Long id;

     @NotBlank(message = "{validation.name.required}")
     @Size(min = 3, max = 50, message = "{validation.name.size}")
     private String name;

     @NotNull(message = "{validation.price.required}")
     @Min(value = 1, message = "{validation.price.amount}")
     private Double price;
}
