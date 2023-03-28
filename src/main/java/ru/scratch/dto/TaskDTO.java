package ru.scratch.dto;

import lombok.Data;
import ru.scratch.domain.Command;
import ru.scratch.domain.Task;
import ru.scratch.domain.User;

import java.util.List;

@Data
public class TaskDTO {
    private long id;
    private User owner;
    private int n;
    private int m;
    private String grid;
    private int x;
    private int y;
    private int angle;
    private String name;
    List<Command> commands;
}