package com.denchev.crud.controller;

import com.denchev.crud.command.task.TaskAddCommand;
import com.denchev.crud.command.task.TaskEditCommand;
import com.denchev.crud.converter.command.TaskCommandConverter;
import com.denchev.crud.entity.Task;
import com.denchev.crud.service.EmployeeService;
import com.denchev.crud.service.TaskService;
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

@Controller
@RequestMapping(path = "/task")
public class TaskController {

    private final EmployeeService employeeService;
    private final TaskService taskService;
    private final TaskCommandConverter taskCommandConverter;

    public TaskController(EmployeeService employeeService,
                          TaskService taskService,
                          TaskCommandConverter taskCommandConverter) {
        this.employeeService = employeeService;
        this.taskService = taskService;
        this.taskCommandConverter = taskCommandConverter;
    }

    @GetMapping(path = "/list")
    public ModelAndView listTasks() {
        return new ModelAndView("task/list", "tasks", taskService.findAll());
    }

    @GetMapping(path = "/new")
    public String addTaskForm(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("addTaskCommand", new TaskAddCommand());
        return "task/add";
    }

    @PostMapping(path = "/new")
    public String handleAddTaskForm(@Valid TaskAddCommand addTaskCommand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "task/add";
        }
        Task task = taskCommandConverter.addTaskCommandToTask(addTaskCommand);
        taskService.save(task);
        return "redirect:/task/list";
    }

    @GetMapping(path = "/{id}/view")
    public String viewTask(@PathVariable("id") Long id, Model model) {
        Optional<Task> taskFromDatabase = taskService.findById(id);
        if (!taskFromDatabase.isPresent()) {
            throw new RuntimeException();
        }
        model.addAttribute("task", taskFromDatabase.get());
        return "task/view";
    }

    @GetMapping(path = "/{id}/update")
    public String updateTaskForm(@PathVariable("id") Long id, Model model) {
        Optional<Task> taskFromDatabase = taskService.findById(id);
        if (!taskFromDatabase.isPresent()) {
            throw new RuntimeException();
        }
        TaskEditCommand taskEditCommand = taskCommandConverter.convertTaskToEditCommand(taskFromDatabase.get());
        model.addAttribute("taskEditCommand", taskEditCommand);
        model.addAttribute("employees", employeeService.findAll());
        return "task/edit";
    }

    @PostMapping(path = "/{id}/update")
    public String handleUpdateTaskForm(@PathVariable("id") Long id, @Valid TaskEditCommand taskEditCommand, BindingResult result) {
        if (result.hasErrors()) {
            return "task/edit";
        }
        Optional<Task> taskFromDatabase = taskService.findById(id);
        if (!taskFromDatabase.isPresent()) {
            throw new RuntimeException();
        }
        Task task = taskFromDatabase.get();
        taskService.updateTaskProperties(task, taskEditCommand);
        taskService.updateTask(task);
        return "redirect:/task/" + task.getId() + "/view";
    }
}
