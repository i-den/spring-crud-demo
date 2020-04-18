package com.denchev.crud.service;

import com.denchev.crud.command.task.TaskEditCommand;
import com.denchev.crud.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll();

    Task save(Task task);

    Optional<Task> findById(Long id);

    void updateTaskProperties(Task task, TaskEditCommand taskEditCommand);

    Task updateTask(Task task);
}
