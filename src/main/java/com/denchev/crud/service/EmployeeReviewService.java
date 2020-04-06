package com.denchev.crud.service;

import com.denchev.crud.command.employee.EmployeeReviewEditCommand;
import com.denchev.crud.entity.EmployeeReview;

import java.util.Optional;

public interface EmployeeReviewService {

    Optional<EmployeeReview> findById(Long id);

    void updateProperties(EmployeeReview employeeReview, EmployeeReviewEditCommand employeeReviewEditCommand);

    EmployeeReview save(EmployeeReview employeeReview);

    void delete(EmployeeReview employeeReview);
}
