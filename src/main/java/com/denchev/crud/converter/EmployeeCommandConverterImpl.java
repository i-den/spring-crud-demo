package com.denchev.crud.converter;

import com.denchev.crud.command.employee.EmployeeAddCommand;
import com.denchev.crud.command.employee.EmployeeEditCommand;
import com.denchev.crud.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCommandConverterImpl implements EmployeeCommandConverter {

    @Override
    public Employee convertAddCommandToEmployee(EmployeeAddCommand employeeAddCommand) {
        Employee employee = new Employee();
        employee.setName(employeeAddCommand.getName());
        employee.setEmail(employeeAddCommand.getEmail());
        employee.setAge(employeeAddCommand.getAge());
        employee.setSubscribed(employeeAddCommand.getSubscribed());
        return employee;
    }

    @Override
    public EmployeeEditCommand convertEmployeeToEditCommand(Employee employee) {
        EmployeeEditCommand employeeEditCommand = new EmployeeEditCommand();
        employeeEditCommand.setId(employee.getId());
        employeeEditCommand.setName(employee.getName());
        employeeEditCommand.setEmail(employee.getEmail());
        employeeEditCommand.setAge(employee.getAge());
        employeeEditCommand.setSubscribed(employee.getSubscribed());
        return employeeEditCommand;
    }
}
