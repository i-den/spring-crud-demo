package com.denchev.crud.validator;

import com.denchev.crud.service.EmployeeService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmployeeUniqueEmailValidator implements ConstraintValidator<EmployeeUniqueEmail, String> {

    private final EmployeeService employeeService;

    public EmployeeUniqueEmailValidator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;
        return !employeeService.findByEmail(value).isPresent();
    }
}
