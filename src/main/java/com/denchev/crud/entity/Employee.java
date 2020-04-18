package com.denchev.crud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Employee")
@Table(name = "employees")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "subscribed", nullable = false)
    private Boolean subscribed;

    @Embedded
    private EmployeeAudit employeeAudit;

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<EmployeeReview> employeeReviews = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "employee_task",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> tasks;

    public void addEmployeeReview(EmployeeReview employeeReview) {
        employeeReview.setEmployee(this);
        employeeReviews.add(employeeReview);
    }

    public void removeEmployeeReview(EmployeeReview employeeReview) {
        employeeReview.setEmployee(null);
        employeeReviews.remove(employeeReview);
    }

    public void addTask(Task task) {
        task.addEmployee(this);
        tasks.add(task);
    }

    public void removeTask(Task task) {
        task.removeEmployee(this);
        tasks.remove(task);
    }
}
