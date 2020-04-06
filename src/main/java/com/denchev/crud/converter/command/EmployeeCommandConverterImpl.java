package com.denchev.crud.converter.command;

import com.denchev.crud.command.employee.EmployeeAddCommand;
import com.denchev.crud.command.employee.EmployeeEditCommand;
import com.denchev.crud.command.employee.EmployeeReviewAddCommand;
import com.denchev.crud.entity.Employee;
import com.denchev.crud.entity.EmployeeAudit;
import com.denchev.crud.entity.EmployeeReview;
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
        employee.setEmployeeAudit(new EmployeeAudit());
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

    @Override
    public EmployeeReview employeeReviewAddCommandToEmployeeReview(EmployeeReviewAddCommand employeeReviewAddCommand) {
        EmployeeReview employeeReview = new EmployeeReview();
        employeeReview.setContent(employeeReviewAddCommand.getContent());
        return employeeReview;
    }
}
