package com.denchev.crud.converter.command;

import com.denchev.crud.command.employee.EmployeeReviewAddCommand;
import com.denchev.crud.command.employee.EmployeeReviewEditCommand;
import com.denchev.crud.entity.EmployeeReview;
import org.springframework.stereotype.Component;

@Component
public class EmployeeReviewCommandConverterImpl implements EmployeeReviewCommandConverter {

    @Override
    public EmployeeReview addCommandToEmployeeReview(EmployeeReviewAddCommand employeeReviewAddCommand) {
        EmployeeReview employeeReview = new EmployeeReview();
        employeeReview.setContent(employeeReviewAddCommand.getContent());
        return employeeReview;
    }

    @Override
    public EmployeeReviewEditCommand employeeReviewToEditCommand(EmployeeReview employeeReview) {
        EmployeeReviewEditCommand employeeReviewEditCommand = new EmployeeReviewEditCommand();
        employeeReviewEditCommand.setId(employeeReview.getId());
        employeeReviewEditCommand.setContent(employeeReview.getContent());
        return employeeReviewEditCommand;
    }

}
