package com.denchev.crud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "EmployeeReview")
@Table(name = "employee_review")
@Getter
@Setter
public class EmployeeReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "content", nullable = false)
    private String content;

}
