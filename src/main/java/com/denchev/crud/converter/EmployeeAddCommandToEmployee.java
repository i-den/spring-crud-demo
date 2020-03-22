package com.denchev.crud.converter;

import com.denchev.crud.command.employee.EmployeeAddCommand;
import com.denchev.crud.entity.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAddCommandToEmployee implements Converter<EmployeeAddCommand, Employee> {

    @Override
    public Employee convert(EmployeeAddCommand src) {
        Employee e = new Employee();
        e.setName(src.getName());
        e.setEmail(src.getEmail());
        e.setAge(src.getAge());
        e.setSubscribed(src.getSubscribed());
        return e;
    }
}
