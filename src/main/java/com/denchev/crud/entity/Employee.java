package com.denchev.crud.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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
    @Size(max = 77, message = "The Employee's name cannot exceed 77 characters")
    @Pattern(regexp = "^[A-Z][a-z]*", message = "The Employee's name must start with a Capital Letter and include only alphabet characters")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @NotNull(message = "The Employee's email is required")
    @NotBlank(message = "The Employee's email cannot be blank")
    @NotEmpty(message = "The Employee's email cannot be empty")
    @Email(message = "The entry is not a valid email address")
    private String email;

    @Column(name ="age", nullable = false)
    @NotNull(message = "Employee's age cannot be null")
    @Min(value = 0, message = "Age must be a positive number")
    @Max(value = 122, message = "You're not that old")
    private Integer age;

    @Column(name = "subscribed", nullable = false)
    @NotNull(message = "Should choose whether the Employee is subscribed or not")
    private Boolean subscribed;

    @Column(name = "date_created", nullable = false)
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "date_updated", nullable = false)
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

}
