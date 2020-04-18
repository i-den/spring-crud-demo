package com.denchev.crud.service;

import com.denchev.crud.command.task.TaskEditCommand;
import com.denchev.crud.entity.Employee;
import com.denchev.crud.entity.Task;
import com.denchev.crud.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;

    public TaskServiceImpl(TaskRepository taskRepository, EmployeeService employeeService) {
        this.taskRepository = taskRepository;
        this.employeeService = employeeService;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void updateTaskProperties(Task task, TaskEditCommand taskEditCommand) {
        task.setDescription(taskEditCommand.getDescription());
        for (Employee employee : task.getEmployees()) {
            employee.getTasks().remove(task);
        }
        task.setEmployees(new HashSet<>());
        for (Long employeeId : taskEditCommand.getEmployeeIds()) {
            Optional<Employee> employeeFromDatabase = employeeService.findById(employeeId);
            if (!employeeFromDatabase.isPresent()) {
                throw new RuntimeException();
            }
            employeeFromDatabase.get().addTask(task);
        }
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.saveAndFlush(task);
    }
}
