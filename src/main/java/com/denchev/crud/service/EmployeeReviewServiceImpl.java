package com.denchev.crud.service;

import com.denchev.crud.command.employee.EmployeeReviewEditCommand;
import com.denchev.crud.entity.EmployeeReview;
import com.denchev.crud.repository.EmployeeReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeReviewServiceImpl implements EmployeeReviewService {

    private final EmployeeReviewRepository employeeReviewRepository;

    public EmployeeReviewServiceImpl(EmployeeReviewRepository employeeReviewRepository) {
        this.employeeReviewRepository = employeeReviewRepository;
    }

    @Override
    public Optional<EmployeeReview> findById(Long id) {
        return employeeReviewRepository.findById(id);
    }

    @Override
    public void updateProperties(EmployeeReview employeeReview, EmployeeReviewEditCommand employeeReviewEditCommand) {
        employeeReview.setContent(employeeReviewEditCommand.getContent());
    }

    @Override
    public EmployeeReview save(EmployeeReview employeeReview) {
        return employeeReviewRepository.save(employeeReview);
    }

    @Override
    public void delete(EmployeeReview employeeReview) {
        employeeReviewRepository.delete(employeeReview);
    }
}
