package com.denchev.crud.controller;

import com.denchev.crud.command.employee.EmployeeAddCommand;
import com.denchev.crud.command.employee.EmployeeEditCommand;
import com.denchev.crud.command.employee.EmployeeReviewAddCommand;
import com.denchev.crud.converter.command.EmployeeCommandConverter;
import com.denchev.crud.entity.Employee;
import com.denchev.crud.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeCommandConverter employeeCommandConverter;

    public EmployeeController(EmployeeService employeeService,
                              EmployeeCommandConverter employeeCommandConverter) {
        this.employeeService = employeeService;
        this.employeeCommandConverter = employeeCommandConverter;
    }

    // Reading All Employees
    @GetMapping(path = "/list", name = "employee_list")
    public ModelAndView employeeList() {
        return new ModelAndView("employee/list", "employees", employeeService.findAll());
    }
    // Reading All Employees

    // Creating New Employee
    @GetMapping(path = "/add", name = "employee_add_get")
    public ModelAndView employeeAddForm() {
        return new ModelAndView("employee/add", "employeeAddCommand", new EmployeeAddCommand());
    }

    @PostMapping(path = "/add", name = "employee_add_post")
    public String handleEmployeeAddForm(@Valid EmployeeAddCommand employeeAddCommand, BindingResult result) {
        if (result.hasErrors()) {
            return "employee/add";
        }
        Employee e = employeeCommandConverter.convertAddCommandToEmployee(employeeAddCommand);
        employeeService.save(e);
        return "redirect:/employee/list";
    }
    // Creating New Employee

    // Read Employee
    @GetMapping(path = "/{id}/view", name = "employee_view")
    public String viewEmployee(@PathVariable("id") Long id, Model model) {
        Optional<Employee> e = employeeService.findById(id);
        if (!e.isPresent()) {
            throw new RuntimeException();
        }
        EmployeeReviewAddCommand employeeReviewAddCommand = new EmployeeReviewAddCommand();
        model.addAttribute("employee", e.get());
        model.addAttribute("employeeReviewAddCommand", employeeReviewAddCommand);
        return "employee/view";
    }
    // Read Employee

    // Updating Employee
    @GetMapping(path = "/{id}/update", name = "employee_update_get")
    public ModelAndView updateEmployeeForm(@PathVariable Long id) {
        Optional<Employee> e = employeeService.findById(id);
        if (!e.isPresent()) {
            throw new RuntimeException();
        }
        EmployeeEditCommand employeeEditCommand = employeeCommandConverter.convertEmployeeToEditCommand(e.get());
        return new ModelAndView("employee/edit", "employeeEditCommand", employeeEditCommand);
    }

    @PostMapping(path = "/{id}/update", name = "employee_update_post")
    public String handleEmployeeUpdateForm(@PathVariable("id") Long id, @Valid EmployeeEditCommand employeeEditCommand, BindingResult result) {
        if (result.hasErrors()) {
            return "employee/edit";
        }
        Optional<Employee> employeeFromDatabase = employeeService.findById(id);
        if (!employeeFromDatabase.isPresent()) {
            throw new RuntimeException("Cannot find an Employee with id: " + id);
        }
        Employee employee = employeeFromDatabase.get();
        if (!employeeService.isUpdatedEmailUnique(employee, employeeEditCommand.getEmail())) {
            result.rejectValue("email", "error.employeeEditCommand", "The email is already taken");
            return "employee/edit";
        }
        employeeService.updateEmployeeProperties(employee, employeeEditCommand);
        employeeService.updateEmployee(employee);
        return "redirect:/employee/" + employee.getId() + "/view";
    }
    // Updating Employee

    // Deleting Employee
    @PostMapping(path = "/{id}/delete", name = "employee_delete")
    public String handleEmployeeDelete(@PathVariable("id") Long id) {
        Optional<Employee> employeeFromDatabase = employeeService.findById(id);
        if (!employeeFromDatabase.isPresent()) {
            throw new RuntimeException("No employee with id: " + id);
        }
        employeeService.deleteEmployee(employeeFromDatabase.get());
        return "redirect:/employee/list";
    }
    // Deleting Employee

}
