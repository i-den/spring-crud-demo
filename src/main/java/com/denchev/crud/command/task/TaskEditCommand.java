package com.denchev.crud.command.task;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TaskEditCommand {

    @NotNull
    private Long id;

    @NotNull
    private List<Long> employeeIds = new ArrayList<>();

    @NotNull
    private String description;

    public void addEmployeeId(Long id) {
        employeeIds.add(id);
    }
}
