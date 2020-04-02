package com.denchev.crud.service;

import com.denchev.crud.command.employee.EmployeeEditCommand;
import com.denchev.crud.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();

    Optional<Employee> findByEmail(String email);

    Employee updateEmployeeProperties(Employee employee, EmployeeEditCommand employeeEditCommand);

    Employee updateEmployee(Employee employee);

    boolean isUpdatedEmailUnique(Employee employee, String email);
}
