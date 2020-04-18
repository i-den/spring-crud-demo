package com.denchev.crud.converter.command;

import com.denchev.crud.command.task.TaskAddCommand;
import com.denchev.crud.command.task.TaskEditCommand;
import com.denchev.crud.entity.Employee;
import com.denchev.crud.entity.Task;
import com.denchev.crud.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskCommandConverterImpl implements TaskCommandConverter {

    private final EmployeeService employeeService;

    public TaskCommandConverterImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public Task addTaskCommandToTask(TaskAddCommand addTaskCommand) {
        Task task = new Task();
        task.setDescription(addTaskCommand.getDescription());
        for (Long employeeId : addTaskCommand.getEmployeeIds()) {
            Optional<Employee> employee = employeeService.findById(employeeId);
            if (!employee.isPresent()) {
                throw new RuntimeException();
            }
            employee.get().addTask(task);
        }
        return task;
    }

    @Override
    public TaskEditCommand convertTaskToEditCommand(Task task) {
        TaskEditCommand taskEditCommand = new TaskEditCommand();
        taskEditCommand.setId(task.getId());
        taskEditCommand.setDescription(task.getDescription());
        for (Employee employee : task.getEmployees()) {
            taskEditCommand.addEmployeeId(employee.getId());
        }
        return taskEditCommand;
    }
}
