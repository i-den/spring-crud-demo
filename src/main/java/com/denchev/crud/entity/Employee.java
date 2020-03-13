package com.denchev.crud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "The Employee's name is required")      // Name: null
    @NotBlank(message = "The Employee's name cannot be blank") // Name: "   "
    @NotEmpty(message = "The Employee's name cannot be empty") // Name: ""
    @Max(value = 77, message = "The Employee's name cannot exceed 77 characters")
    @Pattern(regexp = "^[A-Z][a-z]*", message = "The Employee's name must start with a Capital Letter and include only alphabet characters")
    private String name;

    @Column(name = "email", nullable = false)
    @NotNull(message = "The Employee's email is required")
    @NotBlank(message = "The Employee's email cannot be blank")
    @NotEmpty(message = "The Employee's email cannot be empty")
    @Email(message = "The entry is not a valid email address")
    private String email;


}
