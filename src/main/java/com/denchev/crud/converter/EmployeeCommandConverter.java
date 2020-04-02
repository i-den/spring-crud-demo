package com.denchev.crud.converter;

import com.denchev.crud.command.employee.EmployeeAddCommand;
import com.denchev.crud.command.employee.EmployeeEditCommand;
import com.denchev.crud.entity.Employee;

public interface EmployeeCommandConverter {

    Employee convertAddCommandToEmployee(EmployeeAddCommand employeeAddCommand);

    EmployeeEditCommand convertEmployeeToEditCommand(Employee employee);
}
