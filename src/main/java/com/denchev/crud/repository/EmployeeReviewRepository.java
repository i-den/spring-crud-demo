package com.denchev.crud.repository;

import com.denchev.crud.entity.EmployeeReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeReviewRepository extends JpaRepository<EmployeeReview, Long> {
}
