package com.denchev.crud.service;

import com.denchev.crud.command.employee.EmployeeEditCommand;
import com.denchev.crud.entity.Employee;
import com.denchev.crud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee updateEmployeeProperties(Employee employee, EmployeeEditCommand employeeEditCommand) {
        employee.setName(employeeEditCommand.getName());
        employee.setEmail(employeeEditCommand.getEmail());
        employee.setAge(employeeEditCommand.getAge());
        employee.setSubscribed(employeeEditCommand.getSubscribed());
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public boolean isUpdatedEmailUnique(Employee employee, String email) {
        Optional<Employee> employeeFromDatabase = employeeRepository.findByEmail(email);
        if (!employeeFromDatabase.isPresent()) {
            return true;
        }
        if (employee == employeeFromDatabase.get()) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

}
