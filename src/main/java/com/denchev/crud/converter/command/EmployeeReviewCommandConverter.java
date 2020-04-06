package com.denchev.crud.converter.command;

import com.denchev.crud.command.employee.EmployeeReviewAddCommand;
import com.denchev.crud.command.employee.EmployeeReviewEditCommand;
import com.denchev.crud.entity.EmployeeReview;

public interface EmployeeReviewCommandConverter {

    EmployeeReview addCommandToEmployeeReview(EmployeeReviewAddCommand employeeReviewAddCommand);

    EmployeeReviewEditCommand employeeReviewToEditCommand(EmployeeReview employeeReview);

}
