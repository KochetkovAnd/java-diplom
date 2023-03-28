package ru.scratch.dto;

import lombok.Data;
import ru.scratch.domain.Task;
import ru.scratch.domain.User;
import ru.scratch.domain.keys.UserTaskKey;

@Data
public class UserTaskDTO {
    private UserTaskKey id;
    private User user;
    private Task task;
    private Boolean solved;
    private String solution;
    private int mark;
}
