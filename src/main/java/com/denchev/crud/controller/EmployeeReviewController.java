package com.denchev.crud.controller;

import com.denchev.crud.command.employee.EmployeeReviewAddCommand;
import com.denchev.crud.command.employee.EmployeeReviewEditCommand;
import com.denchev.crud.converter.command.EmployeeReviewCommandConverter;
import com.denchev.crud.entity.Employee;
import com.denchev.crud.entity.EmployeeReview;
import com.denchev.crud.service.EmployeeReviewService;
import com.denchev.crud.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path = "/employee/review")
public class EmployeeReviewController {

    private final EmployeeService employeeService;
    private final EmployeeReviewService employeeReviewService;
    private final EmployeeReviewCommandConverter employeeReviewCommandConverter;

    public EmployeeReviewController(EmployeeService                 employeeService,
                                    EmployeeReviewService           employeeReviewService,
                                    EmployeeReviewCommandConverter  employeeReviewCommandConverter) {
        this.employeeService = employeeService;
        this.employeeReviewService = employeeReviewService;
        this.employeeReviewCommandConverter = employeeReviewCommandConverter;
    }

    @PostMapping(path = "/add")
    public String handleEmployeeCommentAdd(@Valid EmployeeReviewAddCommand employeeReviewAddCommand) {
        Optional<Employee> employeeFromDatabase = employeeService.findById(employeeReviewAddCommand.getId());
        if (!employeeFromDatabase.isPresent()) {
            throw new RuntimeException();
        }
        Employee employee = employeeFromDatabase.get();
        EmployeeReview employeeReview = employeeReviewCommandConverter.addCommandToEmployeeReview(employeeReviewAddCommand);
        employee.addEmployeeReview(employeeReview);
        employeeService.save(employee);
        return "redirect:/employee/" + employee.getId() + "/view";
    }

    @GetMapping(path = "/{id}/edit")
    public ModelAndView updateEmployeeReviewEditForm(@PathVariable("id") Long id) {
        Optional<EmployeeReview> employeeReviewFromDatabase = employeeReviewService.findById(id);
        if (!employeeReviewFromDatabase.isPresent()) {
            throw new RuntimeException();
        }
        EmployeeReviewEditCommand employeeReviewEditCommand = employeeReviewCommandConverter.employeeReviewToEditCommand(employeeReviewFromDatabase.get());
        return new ModelAndView("employee/review/edit", "employeeReviewEditCommand", employeeReviewEditCommand);
    }

    @PostMapping(path = "/{id}/edit")
    public String handleEmployeeReviewEditForm(@PathVariable("id") Long id, @Valid EmployeeReviewEditCommand employeeReviewEditCommand) {
        Optional<EmployeeReview> employeeReviewFromDatabase = employeeReviewService.findById(id);
        if (!employeeReviewFromDatabase.isPresent()) {
            throw new RuntimeException();
        }
        EmployeeReview employeeReview = employeeReviewFromDatabase.get();
        employeeReviewService.updateProperties(employeeReview, employeeReviewEditCommand);
        employeeReviewService.save(employeeReview);
        return "redirect:/employee/" + employeeReview.getEmployee().getId() + "/view";
    }

    @PostMapping(path = "/{id}/delete")
    public String handleEmployeeReviewDelete(@PathVariable("id") Long id) {
        Optional<EmployeeReview> employeeReviewFromDatabase = employeeReviewService.findById(id);
        if (!employeeReviewFromDatabase.isPresent()) {
            throw new RuntimeException();
        }
        EmployeeReview employeeReview = employeeReviewFromDatabase.get();
        employeeReviewService.delete(employeeReview);
        return "redirect:/employee/" + employeeReview.getEmployee().getId() + "/view";
    }
}
