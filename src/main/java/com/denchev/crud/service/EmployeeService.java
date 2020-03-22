package com.denchev.crud.service;

import com.denchev.crud.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findByEmail(String email);
}
