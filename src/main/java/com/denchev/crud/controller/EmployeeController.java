package com.denchev.crud.controller;

import com.denchev.crud.command.employee.EmployeeAddCommand;
import com.denchev.crud.converter.EmployeeAddCommandToEmployee;
import com.denchev.crud.entity.Employee;
import com.denchev.crud.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeAddCommandToEmployee employeeAddCommandToEmployee;

    public EmployeeController(EmployeeService employeeService, EmployeeAddCommandToEmployee employeeAddCommandToEmployee) {
        this.employeeService = employeeService;
        this.employeeAddCommandToEmployee = employeeAddCommandToEmployee;
    }

    @GetMapping(path = "/employees", name = "employee_list")
    public ModelAndView employeeList() {
        return new ModelAndView("employee/list", "employees", employeeService.findAll());
    }


    // Creating New Employee
    @GetMapping(path = "/employee/add", name = "employee_add_get")
    public ModelAndView employeeAddForm() {
        return new ModelAndView("employee/add", "employeeAddCommand", new EmployeeAddCommand());
    }

    @PostMapping(path = "/employee/add", name = "employee_add_post")
    public String handleEmployeeAddForm(@Valid EmployeeAddCommand employeeAddCommand, BindingResult result) {
        if (result.hasErrors()) {
            return "employee/add";
        }
        Employee e = employeeAddCommandToEmployee.convert(employeeAddCommand);
        employeeService.save(e);


        return "redirect:/employees";
    }
    // Creating New Employee

    // Read Employee
    @GetMapping(path = "/employee/{id}/view", name = "employee_view")
    public ModelAndView viewEmployee(@PathVariable Long id) {
        Optional<Employee> e = employeeService.findById(id);
        if (!e.isPresent()) {
            throw new RuntimeException();
        }
        return new ModelAndView("employee/view", "employee", e.get());
    }
    // Read Employee
}
