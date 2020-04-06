package com.denchev.crud.converter.command;

import com.denchev.crud.command.employee.EmployeeAddCommand;
import com.denchev.crud.command.employee.EmployeeEditCommand;
import com.denchev.crud.command.employee.EmployeeReviewAddCommand;
import com.denchev.crud.entity.Employee;
import com.denchev.crud.entity.EmployeeReview;

public interface EmployeeCommandConverter {

    Employee convertAddCommandToEmployee(EmployeeAddCommand employeeAddCommand);

    EmployeeEditCommand convertEmployeeToEditCommand(Employee employee);

    EmployeeReview employeeReviewAddCommandToEmployeeReview(EmployeeReviewAddCommand employeeReviewAddCommand);
}
