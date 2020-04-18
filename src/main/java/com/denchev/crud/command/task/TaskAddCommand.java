package com.denchev.crud.command.task;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TaskAddCommand {

    @NotNull
    private List<Long> employeeIds = new ArrayList<>();

    @NotNull
    private String description;

}
