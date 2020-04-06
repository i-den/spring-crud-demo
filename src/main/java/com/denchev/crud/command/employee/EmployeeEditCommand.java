package com.denchev.crud.command.employee;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EmployeeEditCommand {

    private Long id;

    @NotNull(message = "The Employee's name is required")      // Name: null
    @NotBlank(message = "The Employee's name cannot be only white spaces") // Name: "   "
    @NotEmpty(message = "The Employee's name cannot be empty") // Name: ""
    @Size(max = 77, message = "The Employee's name cannot exceed 77 characters")
    @Pattern(regexp = "^[A-Z][a-z]*", message = "The Employee's name must start with a Capital Letter and include only alphabet characters")
    private String name;

    @NotNull(message = "The Employee's email is required")
    @NotBlank(message = "The Employee's email cannot be only white spaces")
    @NotEmpty(message = "The Employee's email cannot be empty")
    @Email(message = "The entry is not a valid email address")
    private String email;

    @NotNull(message = "The Employee's age must be a valid positive number")
    @Min(value = 0, message = "Age must be a positive number")
    @Max(value = 122, message = "You're not that old")
    private Integer age;

    @NotNull(message = "Should choose whether the Employee is subscribed or not")
    private Boolean subscribed;
}
