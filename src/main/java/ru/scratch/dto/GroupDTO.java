package ru.scratch.dto;

import lombok.Data;
import ru.scratch.domain.Task;
import ru.scratch.domain.User;
import java.util.List;

@Data
public class GroupDTO {
    private long id;
    private String name;
    private User teacher;
    List<Task> tasks;
    List<User> students;
}
