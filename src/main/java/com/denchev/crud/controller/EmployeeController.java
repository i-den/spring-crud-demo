package com.denchev.crud.controller;

import com.denchev.crud.command.EmployeeAddCommand;
import com.denchev.crud.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping(path = "/employees", name = "employee_list")
    public ModelAndView employeeList() {
        return new ModelAndView("employee/list", "employees", employeeRepository.findAll());
    }

    @GetMapping(path = "/employee/add", name = "employee_add_get")
    public ModelAndView employeeAddForm() {
        return new ModelAndView("employee/add", "employee", new EmployeeAddCommand());
    }

    @PostMapping(path = "/employee/add", name = "employee_add_post")
    public String handleEmployeeAddForm(@Valid EmployeeAddCommand employeeAddCommand, BindingResult result) {
        if (result.hasErrors()) {

        }

        return null;
    }
}
