package com.denchev.crud.converter.command;

import com.denchev.crud.command.task.TaskAddCommand;
import com.denchev.crud.command.task.TaskEditCommand;
import com.denchev.crud.entity.Task;

public interface TaskCommandConverter {

    Task addTaskCommandToTask(TaskAddCommand addTaskCommand);

    TaskEditCommand convertTaskToEditCommand(Task task);
}
